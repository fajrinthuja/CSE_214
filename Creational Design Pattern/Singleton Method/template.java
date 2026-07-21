import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// -------------------------------------------------------------
// 1. UNSAFE Lazy Singleton (Instance created on first call, NOT thread-safe)
// -------------------------------------------------------------
class UnsafeLazySingleton {
    private static UnsafeLazySingleton instance = null; // Lazy: starts null

    private UnsafeLazySingleton() {
        System.out.println("   [UnsafeLazySingleton] Constructor executed!");
    }

    public static UnsafeLazySingleton getInstance() {
        if (instance == null) { // Lazy check
            try {
                // Artificial delay to expose the race condition
                Thread.sleep(10); 
            } catch (InterruptedException ignored) {}
            
            instance = new UnsafeLazySingleton();
        }
        return instance;
    }
}

// -------------------------------------------------------------
// 2. SAFE Lazy Singleton (Double-Checked Locking)
// Instance created ONLY on first call, completely thread-safe
// -------------------------------------------------------------
class SafeLazySingleton {
    // 'volatile' prevents instruction reordering during lazy creation
    private static volatile SafeLazySingleton instance = null; // Lazy: starts null

    private SafeLazySingleton() {
        System.out.println("   [SafeLazySingleton] Constructor executed!");
    }

    public static SafeLazySingleton getInstance() {
        // 1st Check: Avoids synchronization penalty if instance is already created
        if (instance == null) { 
            synchronized (SafeLazySingleton.class) {
                // 2nd Check: Guarantees only one thread constructs the instance
                if (instance == null) { 
                    instance = new SafeLazySingleton();
                }
            }
        }
        return instance;
    }
}

// -------------------------------------------------------------
// Main Class
// -------------------------------------------------------------
public class template {

    private static final int THREAD_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("==================================================");
        System.out.println("TEST 1: UNSAFE LAZY SINGLETON");
        System.out.println("==================================================");
        System.out.println("Calling getInstance() across 100 concurrent threads...\n");
        testUnsafeLazySingleton();

        System.out.println("\n==================================================");
        System.out.println("TEST 2: SAFE LAZY SINGLETON (Double-Checked Locking)");
        System.out.println("==================================================");
        System.out.println("Calling getInstance() across 100 concurrent threads...\n");
        testSafeLazySingleton();
    }

    private static void testUnsafeLazySingleton() throws InterruptedException {
        Set<Integer> instanceHashCodes = Collections.newSetFromMap(new ConcurrentHashMap<>());
        
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                try {
                    startSignal.await(); // Hold all threads so they fire simultaneously
                    UnsafeLazySingleton instance = UnsafeLazySingleton.getInstance();
                    instanceHashCodes.add(System.identityHashCode(instance));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneSignal.countDown();
                }
            });
        }

        startSignal.countDown(); // Fire all 100 threads at once
        doneSignal.await();      
        executor.shutdown();

        System.out.println("\nUnique instances found in memory: " + instanceHashCodes.size());
        if (instanceHashCodes.size() > 1) {
            System.out.println("RESULT: NOT THREAD-SAFE ❌ (Race condition created multiple instances)");
        } else {
            System.out.println("RESULT: THREAD-SAFE ✅");
        }
    }

    private static void testSafeLazySingleton() throws InterruptedException {
        Set<Integer> instanceHashCodes = Collections.newSetFromMap(new ConcurrentHashMap<>());

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                try {
                    startSignal.await(); 
                    SafeLazySingleton instance = SafeLazySingleton.getInstance();
                    instanceHashCodes.add(System.identityHashCode(instance));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneSignal.countDown();
                }
            });
        }

        startSignal.countDown(); 
        doneSignal.await();      
        executor.shutdown();

        System.out.println("\nUnique instances found in memory: " + instanceHashCodes.size());
        if (instanceHashCodes.size() == 1) {
            System.out.println("RESULT: THREAD-SAFE ✅ (Only 1 instance lazily created across all threads)");
        } else {
            System.out.println("RESULT: NOT THREAD-SAFE ❌");
        }
    }
}