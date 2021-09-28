package classes.entities;

import classes.crud.CrudBase;

import java.sql.*;

public class Order extends CrudBase {
    public int id;
    public int userId;
    public String status;
    public String createdAt;

    public Order() {}

    public Order(int id, int userId, String status, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public Order create() throws SQLException {
        final String sqlCommand = "INSERT orders(user_id, status, created_at) VALUES(?, ?, ?)";

        Connection connection = this.getSql().getConnection();

        PreparedStatement statement = connection.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, userId);
        statement.setString(2, status);
        statement.setString(3, this.getCurrentTime());

        System.out.println(statement);

        statement.executeUpdate();

        ResultSet response = statement.getGeneratedKeys();

        if (response.next()) {
            int orderId = response.getInt(1);

            return this.findOneById(orderId);
        }

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
    public Order findOneById(int id) throws SQLException {
        final String sqlCommand = "SELECT * FROM orders WHERE id =" + id + " LIMIT 1 ";
        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlCommand);

        ResultSet response = statement.executeQuery();

        if (response.next()) {
            final ProductStatuses status =  ProductStatuses.get(response.getString( "status"));

            return new Order(
                    response.getInt("id"),
                    response.getInt("user_id"),
                    response.getString("status"),
                    response.getString("created_at")
            );
        }

        return null;
    }
}
