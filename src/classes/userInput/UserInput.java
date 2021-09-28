package classes.userInput;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);

    public String requestUserInput() throws UserInputBreakException {
        try {
            String input = this.getTrimmedInput();

            return input;
        } catch (NoSuchElementException err) {
            throw new UserInputBreakException();
        }
    }

    public String requestUserInputMatchedTo(String[] variants) throws UserInputBreakException, UserInputInvalidValueException {
        try {
            String input = this.requestUserInput();

            if (Arrays.asList(variants).contains(input)) {
                return input;
            }

            throw new UserInputInvalidValueException();
        } catch (UserInputBreakException | UserInputInvalidValueException err) {
            throw err;
        }
    }

    public int requestUserInputAsInteger() throws UserInputBreakException, UserInputInvalidValueException {
        try {
            return Integer.parseInt(this.requestUserInput());
        } catch (UserInputBreakException | NumberFormatException err) {
            if (err instanceof  NumberFormatException) {
                throw new UserInputInvalidValueException();
            }

            throw err;
        }

    }

    public boolean requestUserInputYesOrNo() throws UserInputBreakException, UserInputInvalidValueException {
        String[] availableValues = {"y", "n"};

        try {
            String input = this.requestUserInputMatchedTo(availableValues);

            return input.equals("y");
        } catch (UserInputBreakException | UserInputInvalidValueException err) {
            if (err instanceof UserInputInvalidValueException) {
                this.requestUserInputYesOrNo();

                return false;
            }

            throw err;
        }
    }

    private String getTrimmedInput() throws NoSuchElementException {
        return this.scanner.nextLine().trim();
    }
}
