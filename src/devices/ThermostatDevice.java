package devices;

public class ThermostatDevice implements IDevice {
    @Override
    public void turnOff() {
        System.out.println("Thermostat en mode éco.");
    }
}
