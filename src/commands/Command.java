package commands;

import java.util.Scanner;

public interface Command {
    String getDescription();

    void execute(Scanner scanner);
}
