package classes.CommandsPerformer.performers;

import classes.crud.CrudBase;
import classes.userInput.UserInput;

import java.util.Scanner;

public abstract class BaseCommandsPerformer implements ICommandsPerformer {
    Scanner scanner = new Scanner(System.in);
    UserInput userInput = new UserInput();
    CrudBase sql;

    public void print(String message) {
        System.out.println(message);
    }

    abstract void printWelcomeMessage();
    abstract void printErrorMessage();
    abstract void printSuccessMessage();
}
