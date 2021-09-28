package classes.CommandsPerformer.performers;

import classes.entities.Order;
import classes.entities.OrderItem;
import classes.entities.Product;
import classes.userInput.UserInputBreakException;
import classes.userInput.UserInputInvalidValueException;
import classes.views.ProductsTableView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CreateOrderCommandsPerformer extends BaseCommandsPerformer{
    Order orderSqlProvider = new Order();
    OrderItem orderItemSqlProvider = new OrderItem();
    Product productSqlProvider = new Product();

    int productId;
    int quantity;
    @Override
    void printWelcomeMessage() {
        this.print("Create order");
    }

    @Override
    void printErrorMessage() {
        this.print("Error in order creation!");
    }

    @Override
    void printSuccessMessage() {
        this.print("Order was successfully crated!");
    }

    @Override
    public void execute() {
        this.printWelcomeMessage();
        try {
            this.requestOrderDataFromUser();
        } catch(SQLException | UserInputBreakException | UserInputInvalidValueException | ProductFindErrorException err) {
            if(err instanceof ProductFindErrorException) {
                this.print("No product with id<" + ((ProductFindErrorException) err).getMessage() + "> was found!");

                return;
            }
            err.printStackTrace();
        }
    }

    @Override
    public void execute(Map params) {

    }

    private void requestOrderDataFromUser() throws SQLException {
        this.requestProductId();
        this.fetchProductData();
        this.requestProductsQuantity();
        this.createOrder();
        this.printSuccessMessage();
    }

    private void createOrder() throws SQLException {
        Random rand = new Random();
        this.orderSqlProvider.setStatus("checking");
        this.orderSqlProvider.setUserId(rand.nextInt() & Integer.MAX_VALUE);

        Order order = this.orderSqlProvider.create();

        if (order == null) {
            throw new SQLException();
        }

        this.orderItemSqlProvider.setOrderId(order.id);
        this.orderItemSqlProvider.setProductId(this.productId);
        this.orderItemSqlProvider.setQuantity(this.quantity);

        this.orderItemSqlProvider.create();

//        System.out.println("Order -> " + orderItem.orderId + ", " + orderItem.quantity);
    }

    private void fetchProductData() throws SQLException, ProductFindErrorException {
        Product product = this.productSqlProvider.findOneById(this.productId);

        if (product == null) {
            throw new ProductFindErrorException(Integer.toString(this.productId));
        }

        this.printProductTable(product);
    }

    private void requestProductsQuantity() {
        this.print("Enter products quantity:");
        this.quantity = this.userInput.requestUserInputAsInteger();
    }

    private void requestProductId() {
        this.print("Enter product id:");
        this.productId = this.userInput.requestUserInputAsInteger();
    }

    private void printProductTable(Product product) {
        ProductsTableView.printFullTable(new ArrayList(){{
            add(new HashMap(){{
                put("id", product.id);
                put("name", product.name);
                put("price", product.price);
                put("status", product.status);
                put("created_at", product.createdAt);
            }});
        }});
    }
}
