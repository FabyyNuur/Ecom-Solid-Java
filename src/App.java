import commands.Command;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        List<Command> commands = SmartHomeFactory.createCommands();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Maison intelligente — Que voulez-vous faire ?");
            for (int i = 0; i < commands.size(); i++) {
                System.out.println((i + 1) + "- " + commands.get(i).getDescription());
            }
            System.out.println((commands.size() + 1) + "- Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine();

            if (choix >= 1 && choix <= commands.size()) {
                commands.get(choix - 1).execute(scanner);
            } else if (choix == commands.size() + 1) {
                running = false;
            }
        }

        scanner.close();
    }
}
