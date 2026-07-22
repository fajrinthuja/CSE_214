// Product with a Static Inner Builder
class HolidayPackage {
    private final String flight;
    private final String hotel;
    private final String activity;

    // Private constructor: Only the Builder can instantiate this class
    private HolidayPackage(Builder builder) {
        this.flight = builder.flight;
        this.hotel = builder.hotel;
        this.activity = builder.activity;
    }

    // Getters
    public String getFlight() { return flight; }
    public String getHotel() { return hotel; }
    public String getActivity() { return activity; }

    public void showPackageDetails() {
        System.out.println("Holiday Package Details:");
        System.out.println("- Flight: " + flight);
        System.out.println("- Hotel: " + hotel);
        System.out.println("- Activity: " + activity);
        System.out.println();
    }

    // ==========================================
    // Static Inner Builder Class
    // ==========================================
    public static class Builder {
        private String flight;
        private String hotel;
        private String activity;

        // Method chaining (fluent interface)
        public Builder setFlight(String flight) {
            this.flight = flight;
            return this;
        }

        public Builder setHotel(String hotel) {
            this.hotel = hotel;
            return this;
        }

        public Builder setActivity(String activity) {
            this.activity = activity;
            return this;
        }

        // Factory methods for predefined standard packages
        public Builder buildRelaxationPackage() {
            return this.setFlight("Business Class Flight")
                       .setHotel("5-Star Resort")
                       .setActivity("Spa Treatment");
        }

        public Builder buildAdventurePackage() {
            return this.setFlight("Economy Flight")
                       .setHotel("Mountain Cabin")
                       .setActivity("Hiking Tour");
        }

        // Final build method
        public HolidayPackage build() {
            return new HolidayPackage(this);
        }
    }
}

public class BuilderWithFactoryTemplate {

    public static void main(String[] args) {
        HolidayPackage relaxationPackage = new HolidayPackage.Builder()
                .buildRelaxationPackage()
                .build();
        
        relaxationPackage.showPackageDetails();

        // 2. Building standard Adventure Package
        HolidayPackage adventurePackage = new HolidayPackage.Builder()
                .buildAdventurePackage()
                .build();
        
        adventurePackage.showPackageDetails();

        // 3. Custom Package (flexible step-by-step assembly)
        HolidayPackage customPackage = new HolidayPackage.Builder()
                .setFlight("First Class Flight")
                .setHotel("Overwater Villa")
                .setActivity("Scuba Diving")
                .build();

        customPackage.showPackageDetails();
    }
    
}
