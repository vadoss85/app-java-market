package classes.entities;

import classes.crud.CrudBase;

import java.sql.*;

import java.util.ArrayList;


public class Product extends CrudBase {
    public int id;
    public String name;
    public int price;
    public ProductStatuses status;
    public String createdAt;

    public Product() {}

    public Product(int productId) {
        this.id = productId;
    }

    public Product(String name, int price, ProductStatuses status) {
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public Product(int id, String name, int price, ProductStatuses status, String createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStatus(ProductStatuses status) {
        this.status = status;
    }

    @Override
    public Product create() throws SQLException {
        final String sqlCommand = "INSERT products(name, price, status, created_at) VALUES(?, ?, ?, ?)";

        Connection connection = this.getSql().getConnection();

        PreparedStatement statement = connection.prepareStatement(sqlCommand);
        statement.setString(1, name);
        statement.setInt(2, price);
        statement.setString(3, status.status);
        statement.setString(4, this.getCurrentTime());

        System.out.println(statement);

        statement.executeUpdate();
        ResultSet response = statement.getGeneratedKeys();

        if (response.next()) {
            int productId = response.getInt(1);

            return this.findOneById(productId);
        }

        return null;

//        if (response.next()) {
//            final ProductStatuses status =  ProductStatuses.get(response.getString( "status"));
//
//            return new Product(
//                    response.getInt("id"),
//                    response.getString("name"),
//                    response.getInt("price"),
//                    status,
//                    response.getString("created_at")
//            );
//        }
//
//        return null;
    }

    public ArrayList<Product> get() throws SQLException {
        final String sqlCommand = "SELECT * from products";
        ArrayList<Product> results = new ArrayList<>();

        Connection connection = this.getSql().getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlCommand);

        ResultSet response = statement.executeQuery();

        while (response.next()) {
            final ProductStatuses status =  ProductStatuses.get(response.getString( "status"));
            results.add(new Product(
                    response.getInt("id"),
                    response.getString("name"),
                    response.getInt("price"),
                    status,
                    response.getString("created_at")
            ));
        }

        return results;
    }

    public Product findOneById(int id) throws SQLException {
        final String sqlCommand = "SELECT * from products WHERE id =" + id + " LIMIT 1 ";
        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlCommand);

        ResultSet response = statement.executeQuery();

        if (response.next()) {
            final ProductStatuses status =  ProductStatuses.get(response.getString( "status"));

            return new Product(
                    response.getInt("id"),
                    response.getString("name"),
                    response.getInt("price"),
                    status,
                    response.getString("created_at")
            );
        }

        return null;
    }

    public int delete() throws SQLException {
        final String sqlCommand = "DELETE FROM products";

        Connection connection = this.getSql().getConnection();
        Statement statement = connection.createStatement();

        return statement.executeUpdate(sqlCommand);
    }

    @Override
    public int deleteById(int id) throws SQLException {
        final String sqlCommand = "DELETE FROM products WHERE id=" + id;
        Connection connection = this.getSql().getConnection();
        Statement statement = connection.createStatement();

        return statement.executeUpdate(sqlCommand);
    }
}
