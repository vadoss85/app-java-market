package classes.CommandsPerformer.performers;

import classes.CommandsPerformer.performers.BaseCommandsPerformer;
import classes.entities.Product;
import classes.views.ProductsTableView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowProductsPerformer extends BaseCommandsPerformer {
    Product sql;

    public ShowProductsPerformer() {
        this.sql = new Product();
    }

    @Override
    void printWelcomeMessage() {
        this.print("Request for products.");
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
            this.getProducts();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    @Override
    public void execute(Map params) {

    }

    private void getProducts() throws SQLException {
        ArrayList<Product> result = this.sql.get();
        ArrayList viewData = new ArrayList();

        for (int i = 0; i < result.size(); i++) {
            Product product = result.get(i);
            HashMap item = new HashMap(){{
                put("id", product.id);
                put("name", product.name);
                put("price", product.price);
                put("status", product.status);
                put("created_at", product.createdAt);
            }};

            viewData.add(item);
        }
        ProductsTableView.printFullTable(viewData);
    }
}
