package classes.CommandsPerformer.performers;

import classes.entities.Product;
import classes.sql.SQL;
import classes.userInput.UserInputBreakException;
import classes.userInput.UserInputInvalidValueException;

import java.sql.SQLException;
import java.util.Map;

public class DeleteProductsCommandsPerformer extends BaseCommandsPerformer {

    Product sql;
    String password = "pass";
    int productId;

    public DeleteProductsCommandsPerformer() {
        this.sql = new Product();
    }

    @Override
    void printWelcomeMessage() {
        this.print("Delete all products?");
    }

    void printSingleProductWelcomeMessage() {
        this.print("Delete product with id <" + this.productId + ">");
    }

    @Override
    void printErrorMessage() {

    }

    @Override
    void printSuccessMessage() {

    }

    @Override
    public void execute() {
        try {
            this.printWelcomeMessage();
            this.printSubmitMessage();

            boolean isSubmitted = this.requestUserSubmit();

            if (!isSubmitted) {
                return;
            }

            final String pass = this.requestUserPassword();

            if (this.password.equals(pass)) {
                this.deleteAllProducts();
                this.printSuccessMessage();
            } else {
                this.print("Password is wrong!");
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    @Override
    public void execute(Map params) {
        try {
            this.productId = Integer.parseInt((String) params.get("productId"));
            this.printSingleProductWelcomeMessage();
            // TODO make request for product data
            this.printSubmitMessage();

            boolean isSubmitted = this.requestUserSubmit();

            if (!isSubmitted) {
                return;
            }

            int rowsCount = this.deleteProduct();

            if (rowsCount == 0) {
                this.print("There's no product with id <" + this.productId + "> in database");

                return;
            }

            this.print("Product with id <" + this.productId + "> was successfully deleted.");
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public boolean requestUserSubmit() throws UserInputBreakException, UserInputInvalidValueException {
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

    public String requestUserPassword() throws UserInputBreakException, UserInputInvalidValueException {
        this.print("Enter password(pass)");
        return this.userInput.requestUserInput();
    }

    private void printSubmitMessage() {
        this.print("Please confirm(y/n)");
    }

    private void deleteAllProducts() throws SQLException {
        int affectedRowsCount = this.sql.delete();
        this.print("Deleted " + affectedRowsCount + " products");
    }

    private int deleteProduct() throws SQLException {
        int affectedRowsCount = this.sql.deleteById(this.productId);

        return affectedRowsCount;
    }
}
