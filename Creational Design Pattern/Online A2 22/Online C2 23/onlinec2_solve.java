// Product with a Static Inner Builder
class GamingSetup {
    private final String mouse;
    private final String keyboard;
    private final String monitor;

    // Private constructor: Only the Builder can instantiate this class
    private GamingSetup(Builder builder) {
        this.mouse = builder.mouse;
        this.keyboard = builder.keyboard;
        this.monitor = builder.monitor;
    }

    // Getters
    public String getMouse() { return mouse; }
    public String getMonitor() { return monitor; }
    public String getKeyboard() { return keyboard; }

    public void showPackageDetails() {
        System.out.println("Gaming Details:");
        System.out.println("- Mouse: " + mouse);
        System.out.println("- Keyboard: " + keyboard);
        System.out.println("- Monitor: " + monitor);
        System.out.println();
    }

    // ==========================================
    // Static Inner Builder Class
    // ==========================================
    public static class Builder {
        private String mouse;
        private String keyboard;
        private String monitor;

        // Method chaining (fluent interface)
        public Builder setMouse(String mouse) {
            this.mouse = mouse;
            return this;
        }

        public Builder setKeyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Builder setMonitor(String monitor) {
            this.monitor = monitor;
            return this;
        }

        // Factory methods for predefined standard packages
        public Builder buildCompetitiveSetup() {
            return this.setMonitor("240 Hz Monitor")
                        .setKeyboard("Mechanical Keyboard")
                        .setMouse("Lightweight Mouse");
                       
                       
        }

        public Builder buildCasualSetup() {
            return this.setMonitor("Standard Monitor")
                        .setKeyboard("Wireless Keyboard")
                        .setMouse("Wireless Mouse");
                       
                       
        }

        // Final build method
        public GamingSetup build() {
            return new GamingSetup(this);
        }
    }
}

public class onlinec2_solve {
    public static void main(String[] args) {
        GamingSetup gscasual = new GamingSetup.Builder().buildCasualSetup().build();
        GamingSetup gscomp = new GamingSetup.Builder().buildCompetitiveSetup().build();
        gscasual.showPackageDetails();
        gscomp.showPackageDetails();
    }
}
