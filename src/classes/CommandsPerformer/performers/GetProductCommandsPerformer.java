package classes.CommandsPerformer.performers;

import classes.CommandsPerformer.performers.BaseCommandsPerformer;
import classes.entities.Product;

import java.util.Map;

public class GetProductCommandsPerformer extends BaseCommandsPerformer {

    public GetProductCommandsPerformer(Map params) {
        int productId = (int) params.get("productId");
        this.sql = new Product(productId);
    }

    @Override
    void printWelcomeMessage() {

    }

    @Override
    void printErrorMessage() {

    }

    @Override
    void printSuccessMessage() {

    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(Map params) {
//
    }
}