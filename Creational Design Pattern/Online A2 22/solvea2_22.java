// 1. Product
class HolidayPackage {
    private String flight;
    private String hotel;
    private String activity;

    public void setFlight(String flight) { this.flight = flight; }
    public void setHotel(String hotel) { this.hotel = hotel; }
    public void setActivity(String activity) { this.activity = activity; }

    public void showPackageDetails() {
        System.out.println("Holiday Package Details:");
        System.out.println("- Flight: " + flight);
        System.out.println("- Hotel: " + hotel);
        System.out.println("- Activity: " + activity);
        System.out.println();
    }
}

// 2. Builder Interface
interface HolidayPackageBuilder {
    void buildFlight();
    void buildHotel();
    void buildActivity();
    HolidayPackage getPackage();
}

// 3. Concrete Builder 1: Relaxation Package
class RelaxationPackageBuilder implements HolidayPackageBuilder {
    private HolidayPackage packageObj = new HolidayPackage();

    @Override
    public void buildFlight() {
        packageObj.setFlight("Business Class Flight");
    }

    @Override
    public void buildHotel() {
        packageObj.setHotel("5-Star Resort");
    }

    @Override
    public void buildActivity() {
        packageObj.setActivity("Spa Treatment");
    }

    @Override
    public HolidayPackage getPackage() {
        return this.packageObj;
    }
}

// 4. Concrete Builder 2: Adventure Package
class AdventurePackageBuilder implements HolidayPackageBuilder {
    private HolidayPackage packageObj = new HolidayPackage();

    @Override
    public void buildFlight() {
        packageObj.setFlight("Economy Flight");
    }

    @Override
    public void buildHotel() {
        packageObj.setHotel("Mountain Cabin");
    }

    @Override
    public void buildActivity() {
        packageObj.setActivity("Hiking Tour");
    }

    @Override
    public HolidayPackage getPackage() {
        return this.packageObj;
    }
}

// 5. Director (Controls the construction process)
class TravelAgent {
    public HolidayPackage constructPackage(HolidayPackageBuilder builder) {
        builder.buildFlight();
        builder.buildHotel();
        builder.buildActivity();
        return builder.getPackage();
    }
}

// Client Application
public class solvea2_22 {
    public static void main(String[] args) {
        TravelAgent agent = new TravelAgent();

        // Build Relaxation Package
        HolidayPackageBuilder relaxationBuilder = new RelaxationPackageBuilder();
        HolidayPackage relaxationPackage = agent.constructPackage(relaxationBuilder);
        relaxationPackage.showPackageDetails();

        // Build Adventure Package
        HolidayPackageBuilder adventureBuilder = new AdventurePackageBuilder();
        HolidayPackage adventurePackage = agent.constructPackage(adventureBuilder);
        adventurePackage.showPackageDetails();
    }
}