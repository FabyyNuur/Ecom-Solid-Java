package commands;

import controllers.LightController;

import java.util.Scanner;

public class TurnOnLightCommand implements Command {
    private final LightController lightController;

    public TurnOnLightCommand(LightController lightController) {
        this.lightController = lightController;
    }

    @Override
    public String getDescription() {
        return "Allumer une lumière";
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("Entrez le nom de la pièce");
        String room = scanner.nextLine();
        lightController.turnOn(room);
    }
}
