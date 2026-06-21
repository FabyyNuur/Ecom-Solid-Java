package commands;

import controllers.SecurityController;

import java.util.Scanner;

public class LockDoorsCommand implements Command {
    private final SecurityController securityController;

    public LockDoorsCommand(SecurityController securityController) {
        this.securityController = securityController;
    }

    @Override
    public String getDescription() {
        return "Verrouiller les portes";
    }

    @Override
    public void execute(Scanner scanner) {
        securityController.lockDoors();
    }
}
