package classes.entities;

import classes.CommandsPerformer.performers.OrderFindErrorException;
import classes.crud.CrudBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderProductData extends CrudBase {
    public String productName;
    public int orderId;
    public int price;
    public int quantity;
    public String createdAt;

    String sqlSelectCommand = "SELECT o.id, (p.price * oi.quantity) total, p.name, oi.quantity, o.created_at FROM orders o LEFT JOIN order_items oi ON oi.order_id = o.id LEFT JOIN products p ON oi.product_id = p.id";
    String sqlOrderCommand = " ORDER BY o.id ASC";
//    String sqlCommand = this.sqlSelectCommand + " " + this.sqlOrderCommand;
    String sqlFindCommand = "WHERE o.id=";

    public OrderProductData() {}

    public OrderProductData(int orderId, String productName, int price, int quantity, String createdAt) {
        this.orderId = orderId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public ArrayList<OrderProductData> getAll() throws SQLException {
        ArrayList<OrderProductData> results = new ArrayList<>();

        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement(this.sqlSelectCommand + this.sqlOrderCommand);

        System.out.println(statement);

        ResultSet response = statement.executeQuery();

        while (response.next()) {
            System.out.println();
            results.add(new OrderProductData(
                    response.getInt(1),
                    response.getString(3),
                    response.getInt(2),
                    response.getInt(4),
                    response.getString(5)
            ));
        }

        return results;
    }
    @Override
    public OrderProductData create() throws SQLException {
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
    public OrderProductData findOneById(int id) throws SQLException {
        String sqlCommandId = this.sqlSelectCommand + " " + this.sqlFindCommand + id + this.sqlOrderCommand;

        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlCommandId);

        System.out.println(statement);

        ResultSet response = statement.executeQuery();

        if (response.next()) {
            return new OrderProductData(
                response.getInt(1),
                response.getString(3),
                response.getInt(2),
                response.getInt(4),
                response.getString(5)
            );
        }

        return null;
    }
}
