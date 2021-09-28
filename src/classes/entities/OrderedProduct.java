package classes.entities;

import classes.crud.CrudBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderedProduct extends CrudBase {
    public int productId;
    public String name;
    public int quantity;

    public OrderedProduct() {}

    public OrderedProduct(int productId, String name, int quantity) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
    }

    public ArrayList<OrderedProduct> getAll() throws SQLException {
        final String sqlCommand = "SELECT p.name, p.id, SUM(oi.quantity) total FROM order_items oi INNER JOIN products p on p.id = oi.product_id GROUP BY oi.product_id ORDER BY total DESC";
        ArrayList<OrderedProduct> results = new ArrayList<>();

        Connection connection = this.getSql().getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlCommand);

        System.out.println(statement);

        ResultSet response = statement.executeQuery();

        while (response.next()) {
            System.out.println();
            results.add(new OrderedProduct(
                response.getInt(2),
                response.getString(1),
                response.getInt(3)
            ));
        }

        return results;
    }

    @Override
    public OrderedProduct create() throws SQLException {
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
    public OrderedProduct findOneById(int id) throws SQLException {
        return null;
    }
}
