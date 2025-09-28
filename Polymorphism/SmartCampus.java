// Base class
class SmartDevice {
    protected String deviceId;

    public SmartDevice(String deviceId) {
        this.deviceId = deviceId;
    }

    public void showStatus() {
        System.out.println(" SmartDevice " + deviceId + " is connected.");
    }
}

// SmartClassroom
class SmartClassroom extends SmartDevice {
    public SmartClassroom(String deviceId) {
        super(deviceId);
    }

    public void controlLighting(boolean on) {
        System.out.println(" Classroom " + deviceId + ": Lighting " + (on ? "ON" : "OFF"));
    }

    public void controlAC(boolean on) {
        System.out.println(" Classroom " + deviceId + ": AC " + (on ? "ON" : "OFF"));
    }

    public void controlProjector(boolean on) {
        System.out.println(" Classroom " + deviceId + ": Projector " + (on ? "ON" : "OFF"));
    }
}

// SmartLab
class SmartLab extends SmartDevice {
    public SmartLab(String deviceId) {
        super(deviceId);
    }

    public void manageEquipment(String equipment) {
        System.out.println(" Lab " + deviceId + ": Managing equipment " + equipment);
    }

    public void activateSafetySystem() {
        System.out.println(" Lab " + deviceId + ": Safety system activated!");
    }
}

// SmartLibrary
class SmartLibrary extends SmartDevice {
    public SmartLibrary(String deviceId) {
        super(deviceId);
    }

    public void trackOccupancy(int people) {
        System.out.println(" Library " + deviceId + ": Current occupancy = " + people);
    }

    public void checkBookAvailability(String book) {
        System.out.println(" Library " + deviceId + ": Checking availability for \"" + book + "\"");
    }
}

// Test class
public class SmartCampus {
    public static void main(String[] args) {
        // Mixed devices (Upcasting)
        SmartDevice[] devices = new SmartDevice[3];
        devices[0] = new SmartClassroom("C101");
        devices[1] = new SmartLab("L201");
        devices[2] = new SmartLibrary("LIB01");

        // Process devices safely
        for (SmartDevice d : devices) {
            d.showStatus();

            if (d instanceof SmartClassroom) {
                SmartClassroom classroom = (SmartClassroom) d;
                classroom.controlLighting(true);
                classroom.controlAC(false);
                classroom.controlProjector(true);
            } else if (d instanceof SmartLab) {
                SmartLab lab = (SmartLab) d;
                lab.manageEquipment("Microscope");
                lab.activateSafetySystem();
            } else if (d instanceof SmartLibrary) {
                SmartLibrary library = (SmartLibrary) d;
                library.trackOccupancy(45);
                library.checkBookAvailability("Data Structures in Java");
            }

            System.out.println("--------------------------------");
        }
    }
}
