package commands;

import devices.IDevice;

import java.util.List;
import java.util.Scanner;

public class TurnOffAllCommand implements Command {
    private final List<IDevice> devices;

    public TurnOffAllCommand(List<IDevice> devices) {
        this.devices = devices;
    }

    @Override
    public String getDescription() {
        return "Extinction globale";
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("Extinction globale...");
        for (IDevice device : devices) {
            device.turnOff();
        }
    }
}
