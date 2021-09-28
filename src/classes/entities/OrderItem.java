package classes.entities;

import classes.crud.CrudBase;
import java.sql.*;

public class OrderItem extends CrudBase {
    public int orderId;
    public int productId;
    public int quantity;

    public OrderItem() {}

    public OrderItem(int orderId, int productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public OrderItem create() throws SQLException {
        final String sqlCommand = "INSERT order_items (order_id, product_id, quantity) VALUES(?, ?, ?)";

        Connection connection = this.getConnection();

        PreparedStatement statement = connection.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, orderId);
        statement.setInt(2, productId);
        statement.setInt(3, quantity);

        System.out.println(statement);

        statement.executeUpdate();

//        ResultSet response = statement.getGeneratedKeys();
//
//        if (response.next()) {
//            int orderItemId = response.getInt(1);
//
//            return this.findOneById(orderItemId);
//        }

        return null;
    }

    @Override
    public int delete() throws SQLException {
        return 0;
    }

    @Override
    public int deleteById(int id) throws SQLException {
        return 0;
    }

    @Override
    public OrderItem findOneById(int id) throws SQLException {
        final String sqlCommand = "SELECT * FROM order_items WHERE id =" + id + " LIMIT 1 ";
        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlCommand);

        ResultSet response = statement.executeQuery();

        if (response.next()) {
            return new OrderItem(
                    response.getInt("order_id"),
                    response.getInt("product_id"),
                    response.getInt("quantity")
            );
        }

        return null;
    }
}
