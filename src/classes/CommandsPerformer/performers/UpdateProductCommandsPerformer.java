package classes.CommandsPerformer.performers;

//import classes.CommandsPerformer.performers.BaseCommandsPerformer;

import classes.CommandsPerformer.performers.BaseCommandsPerformer;

import java.util.Map;

public class UpdateProductCommandsPerformer extends BaseCommandsPerformer {

    @Override
    public void execute() {

    }

    @Override
    public void execute(Map params) {
//        int productID = params.get("productId");
    }

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
