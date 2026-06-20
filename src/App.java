import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        SmartHomeApp app = new SmartHomeApp();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Maison intelligente — Que voulez-vous faire ?");
            System.out.println("1- Allumer une lumière");
            System.out.println("2- Régler le thermostat");
            System.out.println("3- Verrouiller les portes");
            System.out.println("4- Extinction globale");
            System.out.println("5- Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine();

            if (choix == 1) {
                System.out.println("Entrez le nom de la pièce");
                String room = scanner.nextLine();
                app.turnOnLight(room);
            } else if (choix == 2) {
                System.out.println("Entrez la température souhaitée");
                float temp = scanner.nextFloat();
                app.setTemperature(temp);
            } else if (choix == 3) {
                app.lockDoors();
            } else if (choix == 4) {
                app.turnOffAll();
            } else if (choix == 5) {
                running = false;
            }
        }

        scanner.close();
    }
}
