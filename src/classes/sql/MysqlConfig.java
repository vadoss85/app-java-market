package classes.sql;

import classes.sql.IMysqlConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class MysqlConfig implements IMysqlConfig {
    String host = "localhost:3306";
    String user = "admin";
    String protocol = "jdbc:mysql://";
    String password = "_PASSword1_";
    String table_name = "java_app_market_db";

    MysqlConfig() {
        Properties localProps = this.findLocalPropertiesFile();

        if (localProps != null) {
            this.setLocalPropertiesToConfig(localProps);
        }
    }

    public void setLocalPropertiesToConfig(Properties localProps) {
        String host = localProps.getProperty("mysql.host");
        String user = localProps.getProperty("mysql.user");
        String password = localProps.getProperty("mysql.password");
        String tableName = localProps.getProperty("mysql.table_name");

        if (host != null) {
            this.setHost(host);
        }

        if (user != null) {
            this.setUser(user);
        }

        if (password != null) {
            this.setPassword(password);
        }

        if (tableName != null) {
            this.setTableName(tableName);
        }
    }

    private Properties findLocalPropertiesFile() {
        Path filePath = Paths.get(System.getProperty("user.dir"), "local.properties");
        Properties localProperties = new Properties();

        try {
            localProperties.load(new FileInputStream(filePath.toString()));

            return localProperties;
        } catch(IOException err) {
//            err.printStackTrace();
        }

        return null;
    }

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

    public void setHost(String host) {
        this.host = host;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTableName(String tableName) {
        this.table_name = tableName;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
