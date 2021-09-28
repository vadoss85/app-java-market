package classes.CommandsPerformer.performers;

import classes.entities.OrderedProduct;
import classes.entities.Product;
import classes.views.OrderedProductsTableView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowOrderedProductsCommandsPerformer extends BaseCommandsPerformer {
    OrderedProduct sql = new OrderedProduct();
    @Override
    void printWelcomeMessage() {
        this.print("Show ordered products");
    }

    @Override
    void printErrorMessage() {

    }

    @Override
    void printSuccessMessage() {
        this.print("Done!");
    }

    @Override
    public void execute() {
        try {
            this.printWelcomeMessage();
            this.getOrderedProducts();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    private void getOrderedProducts() throws SQLException {
        ArrayList<OrderedProduct> orderedProducts = this.sql.getAll();
        ArrayList viewData = new ArrayList();

        for (int i = 0; i < orderedProducts.size(); i++) {
            OrderedProduct product = orderedProducts.get(i);
            HashMap item = new HashMap(){{
                put("name", product.name);
                put("total", product.quantity);
            }};

            viewData.add(item);
        }

        this.print("List of all products, which have been ordered at least once, with total ordered quantity sorted descending by the quantity");
        OrderedProductsTableView.printTable(viewData);
    }

    @Override
    public void execute(Map params) {

    }
}
