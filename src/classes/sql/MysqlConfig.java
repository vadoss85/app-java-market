package classes.sql;

import classes.sql.IMysqlConfig;

public class MysqlConfig implements IMysqlConfig {
    String host = "localhost:3306/";
    String user = "admin";
    String protocol = "jdbc:mysql://";
    String password = "_PASSword1_";
    String table_name = "java_app_market_db";

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getDatabase() {
        return table_name;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }
}
