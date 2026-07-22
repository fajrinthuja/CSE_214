// Product
interface Transport {
    void deliver();
}

class Truck implements Transport {
    public void deliver() { System.out.println("Truck delivered"); }
}

class Ship implements Transport {
    public void deliver() { System.out.println("Ship delivered"); }
}

// Creator (Abstract Base Class)
abstract class Creator {
    // Factory Method
    protected abstract Transport createProduct();

    // Business Logic using the product
    public void execute() {
        Transport p = createProduct(); // Creation deferred to subclass
        p.deliver();
    }
}

// Concrete Creators
class TruckCreator extends Creator {
    @Override
    protected Transport createProduct() {
        return new Truck();
    }
}

class ShipCreator extends Creator {
    @Override
    protected Transport createProduct() {
        return new Ship();
    }
}

public class solvea1_22 {
    public static void main(String[] args) {
        ShipCreator a = new ShipCreator();
        a.execute();
        TruckCreator b = new TruckCreator();
        b.execute();
    }
}
