public class SmartHomeApp {
    public void turnOnLight(String room) {
        System.out.println("Lumière allumée dans : " + room);
    }

    public void setTemperature(float temp) {
        System.out.println("Thermostat réglé sur " + temp + "°C");
    }

    public void lockDoors() {
        System.out.println("Portes verrouillées.");
    }

    public void turnOffAll() {
        System.out.println("Extinction globale...");
        System.out.println("Lumières éteintes.");
        System.out.println("Thermostat en mode éco.");
        System.out.println("Alarme DÉSACTIVÉE (Faille de sécurité !).");
    }
}
