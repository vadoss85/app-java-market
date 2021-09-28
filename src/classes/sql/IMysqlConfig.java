package classes.sql;

public interface IMysqlConfig {
    String getProtocol();
    String getHost();
    String getUser();
    String getPassword();
    String getDatabase();
}
