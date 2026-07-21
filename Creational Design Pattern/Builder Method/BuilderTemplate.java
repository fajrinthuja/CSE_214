// 1. The Complex Object (Product)
class Computer {
    // Required parameters
    private final String HDD;
    private final String RAM;

    // Optional parameters
    private final boolean isGraphicsCardEnabled;
    private final boolean isBluetoothEnabled;

    // Private constructor: Forces creation ONLY through the Builder
    private Computer(ComputerBuilder builder) {
        this.HDD = builder.HDD;
        this.RAM = builder.RAM;
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    // Getters only (Immutability is a major benefit of this pattern)
    public String getHDD() { return HDD; }
    public String getRAM() { return RAM; }
    public boolean isGraphicsCardEnabled() { return isGraphicsCardEnabled; }
    public boolean isBluetoothEnabled() { return isBluetoothEnabled; }

    @Override
    public String toString() {
        return "Computer [RAM=" + RAM + ", HDD=" + HDD + 
               ", GraphicsCard=" + isGraphicsCardEnabled + 
               ", Bluetooth=" + isBluetoothEnabled + "]";
    }

    // 2. The Builder Class (Static Nested Class)
    public static class ComputerBuilder {
        // Required parameters
        private final String HDD;
        private final String RAM;

        // Optional parameters initialized to default values
        private boolean isGraphicsCardEnabled = false;
        private boolean isBluetoothEnabled = false;

        // Constructor for required parameters
        public ComputerBuilder(String HDD, String RAM) {
            this.HDD = HDD;
            this.RAM = RAM;
        }

        // Setter-like methods for optional parameters (return 'this' for method chaining)
        public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        // The final method to instantiate the target object
        public Computer build() {
            return new Computer(this);
        }
    }
}

// 3. Demo / Usage
public class BuilderTemplate {
    public static void main(String[] args) {
        // Building a basic computer with default optional features
        Computer basicComp = new Computer.ComputerBuilder("500 GB", "8 GB")
                .build();

        // Building a high-end computer with custom optional features via method chaining
        Computer gamingComp = new Computer.ComputerBuilder("1 TB", "32 GB")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .build();

        System.out.println(basicComp);
        System.out.println(gamingComp);
    }
}