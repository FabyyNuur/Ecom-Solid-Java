package devices;

public class SecurityAlarmDevice implements IDevice {
    @Override
    public void turnOff() {
        System.out.println("Alarme DÉSACTIVÉE (Faille de sécurité !).");
    }
}
