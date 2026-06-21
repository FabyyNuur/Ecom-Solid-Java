package commands;

import controllers.ThermostatController;

import java.util.Scanner;

public class SetTemperatureCommand implements Command {
    private final ThermostatController thermostatController;

    public SetTemperatureCommand(ThermostatController thermostatController) {
        this.thermostatController = thermostatController;
    }

    @Override
    public String getDescription() {
        return "Régler le thermostat";
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("Entrez la température souhaitée");
        float temp = scanner.nextFloat();
        scanner.nextLine();
        thermostatController.setTemperature(temp);
    }
}
