package devices;

public class LightDevice implements IDevice {
    @Override
    public void turnOff() {
        System.out.println("Lumières éteintes.");
    }
}
