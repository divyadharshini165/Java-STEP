import java.util.*;

// Base class
abstract class SmartDevice {
    protected String deviceId;
    protected String location;
    protected boolean online;

    public SmartDevice(String deviceId, String location) {
        this.deviceId = deviceId;
        this.location = location;
        this.online = true;
    }

    public boolean isOnline() { return online; }
    public void setOnline(boolean online) { this.online = online; }

    // Polymorphic method: each device implements its own action
    public abstract void operate();
}

// Smart TV
class SmartTV extends SmartDevice {
    private int volume = 15;
    private int channel = 1;

    public SmartTV(String id, String location) { super(id, location); }

    @Override
    public void operate() {
        System.out.println(deviceId + " (TV) is operating. Channel: " + channel + ", Volume: " + volume);
    }

    public void setChannel(int ch) { this.channel = ch; }
    public void setVolume(int vol) { this.volume = vol; }
}

// Smart Thermostat
class SmartThermostat extends SmartDevice {
    private double temp = 22.0;

    public SmartThermostat(String id, String location) { super(id, location); }

    @Override
    public void operate() {
        System.out.println(deviceId + " (Thermostat) is operating. Temp: " + temp + "°C");
    }

    public void setTemperature(double temp) { this.temp = temp; }
}

// Smart Security System
class SmartSecuritySystem extends SmartDevice {
    private boolean alarmArmed = false;

    public SmartSecuritySystem(String id, String location) { super(id, location); }

    @Override
    public void operate() {
        System.out.println(deviceId + " (Security) is operating. Alarm armed: " + alarmArmed);
    }

    public void armAlarm() { alarmArmed = true; }
}

// Smart Kitchen Appliance
class SmartKitchenAppliance extends SmartDevice {
    private String recipe = "Idle";

    public SmartKitchenAppliance(String id, String location) { super(id, location); }

    @Override
    public void operate() {
        System.out.println(deviceId + " (Kitchen) is cooking: " + recipe);
    }

    public void startRecipe(String recipe) { this.recipe = recipe; }
}

// Controller
class SmartHomeController {
    public void operateAllDevices(List<SmartDevice> devices) {
        for (SmartDevice d : devices) {
            if (d.isOnline()) {
                d.operate();  // Polymorphic call
            } else {
                System.out.println(d.deviceId + " is offline, skipping.");
            }
        }
    }
}

// Demo
public class SmartHomeDemo {
    public static void main(String[] args) {
        List<SmartDevice> devices = new ArrayList<>();
        devices.add(new SmartTV("TV-001", "Living Room"));
        devices.add(new SmartThermostat("TSTAT-01", "Hallway"));
        devices.add(new SmartSecuritySystem("SEC-Front", "Entrance"));
        devices.add(new SmartKitchenAppliance("APPL-OVEN1", "Kitchen"));

        SmartHomeController controller = new SmartHomeController();
        controller.operateAllDevices(devices);
    }
}
