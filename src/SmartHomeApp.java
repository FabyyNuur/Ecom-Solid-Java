import controllers.LightController;
import controllers.SecurityController;
import controllers.ThermostatController;
import devices.IDevice;

import java.util.List;

public class SmartHomeApp {
    private final LightController lightController;
    private final ThermostatController thermostatController;
    private final SecurityController securityController;
    private final List<IDevice> devices;

    public SmartHomeApp(LightController lightController,
                        ThermostatController thermostatController,
                        SecurityController securityController,
                        List<IDevice> devices) {
        this.lightController = lightController;
        this.thermostatController = thermostatController;
        this.securityController = securityController;
        this.devices = devices;
    }

    public void turnOnLight(String room) {
        lightController.turnOn(room);
    }

    public void setTemperature(float temp) {
        thermostatController.setTemperature(temp);
    }

    public void lockDoors() {
        securityController.lockDoors();
    }

    public void turnOffAll() {
        System.out.println("Extinction globale...");
        for (IDevice device : devices) {
            device.turnOff();
        }
    }
}
