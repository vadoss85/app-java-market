package classes.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {
    Connection connection;
    MysqlConfig config = new MysqlConfig();

    public Connection getConnection() throws SQLException {
        if (connection != null) {
            return connection;
        }

        try {
            connection = DriverManager
                    .getConnection(
                            config.getProtocol() + config.getHost() + config.getDatabase(),
                            config.getUser(),
                            config.getPassword()
                    );
            return connection;
        } catch (SQLException e) {
            throw e;
        }
    }
}
