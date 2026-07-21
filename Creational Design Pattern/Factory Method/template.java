// Product
interface Product {
    void performAction();
}

class ConcreteProductA implements Product {
    public void performAction() { System.out.println("Product A working"); }
}

class ConcreteProductB implements Product {
    public void performAction() { System.out.println("Product B working"); }
}

// Creator (Abstract Base Class)
abstract class Creator {
    // Factory Method
    protected abstract Product createProduct();

    // Business Logic using the product
    public void execute() {
        Product p = createProduct(); // Creation deferred to subclass
        p.performAction();
    }
}

// Concrete Creators
class CreatorA extends Creator {
    @Override
    protected Product createProduct() {
        return new ConcreteProductA();
    }
}

class CreatorB extends Creator {
    @Override
    protected Product createProduct() {
        return new ConcreteProductB();
    }
}

public class template {
    public static void main(String[] args) {
        CreatorA a = new CreatorA();
        a.execute();
        CreatorB b = new CreatorB();
        b.execute();
    }
}
