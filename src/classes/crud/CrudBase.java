package classes.crud;

import classes.entities.Product;
import classes.sql.SQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

interface ICrudBase<E extends ICrudBase> {
    E create() throws SQLException;
//    public void update();
    int delete() throws SQLException;
    int deleteById(int id) throws SQLException;
    E findOneById(int id) throws SQLException;
}

public abstract class CrudBase implements ICrudBase {
    private SQL sql = new SQL();

    public SQL getSql() {
        return sql;
    }

    public String getCurrentTime() {
        String pattern = "YYYY-MM-dd HH:MM:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        return formatter.format(new Date());
    }

    public Connection getConnection() throws SQLException {
         return this.getSql().getConnection();
    }
}
