package classes.CommandsPerformer.performers;

import classes.entities.OrderProductData;
import classes.entities.Product;
import classes.views.OrderedProductsTableView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListOrdersCommandsPerformer extends BaseCommandsPerformer {
    OrderProductData orderProductSql = new OrderProductData();

    @Override
    void printWelcomeMessage() {
        this.print("Show orders");
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
        this.printWelcomeMessage();

        try {
            this.loadOrders();
            this.printSuccessMessage();
        } catch(SQLException err) {
            err.printStackTrace();
        }
    }

    @Override
    public void execute(Map params) {
        String orderId = (String) params.get("orderId");
        this.printWelcomeMessage();

        try {
            this.loadOrder(Integer.parseInt(orderId));
            this.printSuccessMessage();
        } catch(SQLException | OrderFindErrorException err) {
            if (err instanceof OrderFindErrorException) {
                System.out.println("No order with id<" + orderId + "> was found");
                return;
            }
            err.printStackTrace();
        }
    }

    private void loadOrders() throws SQLException {
        ArrayList<OrderProductData> result = this.orderProductSql.getAll();

        OrderedProductsTableView.printOrderProductDataTable(createViewArray(result));
    }

    private void loadOrder(int orderId) throws SQLException, OrderFindErrorException {
        OrderProductData order = this.orderProductSql.findOneById(orderId);

        if (order == null) {
            throw new OrderFindErrorException(Integer.toString(orderId));
        }

        OrderedProductsTableView.printOrderProductDataTable(
            createViewArray(new ArrayList(){{
                add(order);
            }})
        );
    }

    private ArrayList<HashMap> createViewArray(ArrayList<OrderProductData> orders) {
        ArrayList viewData = new ArrayList();

        for (int i = 0; i < orders.size(); i++) {
            OrderProductData order = orders.get(i);

            HashMap item = new HashMap(){{
                put("id", order.orderId);
                put("name", order.productName);
                put("price", order.price);
                put("quantity", order.quantity);
                put("createdAt", order.createdAt);
            }};

            viewData.add(item);
        }

        return viewData;
    }
}
