package classes.CommandsPerformer.performers;

import classes.CommandsPerformer.performers.BaseCommandsPerformer;
import classes.entities.Product;
import classes.entities.ProductStatuses;
import classes.userInput.UserInputBreakException;
import classes.userInput.UserInputInvalidValueException;
import classes.views.ProductsTableView;

import java.sql.SQLException;
import java.util.Map;

public class CreateProductCommandsPerformer extends BaseCommandsPerformer {
    String name;
    int price = 0;
    ProductStatuses status;
    Product sql;

    public CreateProductCommandsPerformer() {
        this.sql = new Product();
    }

    public void execute() {
        this.printWelcomeMessage();

        try {
            this.requestProductName();
            this.requestProductPrice();
            this.requestProductStatus();
            this.showTotalDataTable();
            boolean isUserSubmitOperation = this.requestUserSubmit();

            if (isUserSubmitOperation) {
                this.createProduct();
                this.printSuccessMessage();
                return;
            }

            this.print("Canceled!");

        } catch (UserInputBreakException | UserInputInvalidValueException | SQLException err) {

            if (err instanceof SQLException) {
                this.print("Error with connection to Database.");
                this.print("Exiting...");
                ((SQLException) err).printStackTrace();

                return;
            }

            if (err instanceof UserInputBreakException) {
                this.print("Operation was canceled by user.");

                return;
            }

            if (err instanceof UserInputInvalidValueException) {
                this.print("Not valid value.");

                return;
            }

            this.printErrorMessage();
        }
    }

    @Override
    public void execute(Map params) {

    }

    private void requestProductName() {
        this.print("Product name:");

        while (this.name == null) {
            String input = this.userInput.requestUserInput();
            if (input.length() > 0) {
                this.name = input;
            }
        }
    }
    private void requestProductPrice() {
        this.print("Product price:");

        try {
            while (this.price == 0) {
                int input = this.userInput.requestUserInputAsInteger();

                if (input == 0) {
                    throw new UserInputInvalidValueException();
                }

                if (input > 0) {
                    this.price = input;
                }
            }
        } catch (UserInputInvalidValueException | UserInputBreakException err) {
            if (err instanceof UserInputInvalidValueException) {
                this.printPriceValidationErrorMessage();
                this.requestProductPrice();

                return;
            }

            throw err;
        }
    }

    private void requestProductStatus() {
        this.print("Product status:");
        this.print("[Available values: 1 - In stock; 2 - Running low; 3 - Out of stock]");

        String[] availableValues = {"1", "2", "3"};

        try {
            while (this.status == null) {
                String input = this.userInput.requestUserInputMatchedTo(availableValues);

                this.status = this.inputValToStatus(input);
            }
        } catch (UserInputInvalidValueException | UserInputBreakException err) {
            if (err instanceof UserInputInvalidValueException) {
                this.printStatusValidationErrorMessage();
                this.requestProductStatus();

                return;
            }

            throw err;
        }
    }

    private void showTotalDataTable() {
        this.printTableData();
        this.printSubmitMessage();
    }

    private void printTableData() {
        ProductsTableView.printPreviewTable(this.name,  Integer.toString(this.price), this.status.status);
    }

    private boolean requestUserSubmit() {
        try {
            return this.userInput.requestUserInputYesOrNo();
        } catch (UserInputInvalidValueException | UserInputBreakException err) {
            if (err instanceof UserInputInvalidValueException) {
                this.printSubmitMessage();
                this.requestUserSubmit();

                return false;
            }

            throw err;
        }
    }

    private void createProduct() throws SQLException {
        this.sql.setName(this.name);
        this.sql.setPrice(this.price);
        this.sql.setStatus(this.status);

        this.sql.create();
    }

    private ProductStatuses inputValToStatus(String input) {
        switch (input) {
            case "2": {
                return ProductStatuses.RUNNING_LOW;
            }
            case "3": {
                return ProductStatuses.OUT_OF_STOCK;
            }
            default: {
                return ProductStatuses.IN_STOCK;
            }
        }
    }

    private void printSubmitMessage() {
        this.print("Please submit product data(y/n):");
    }

    private void printPriceValidationErrorMessage() {
        this.print("Price should be a number greater then 0.");
    }
    private void printStatusValidationErrorMessage() {
        this.print("Status should be one of following numbers: 1, 2 or 3");
    }

    @Override
    public void printWelcomeMessage() {
        this.print("Please provide product data.");
    }

    @Override
    public void printErrorMessage() {
        this.print("Exit...");
    }

    @Override
    public void printSuccessMessage() {
        this.print("Product was successfully created.");
    }
}
