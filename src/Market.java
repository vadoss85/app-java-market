import classes.CommandsPerformer.CommandsPerformer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Market {
    public static void main(String[] args) {
//        TODO change classes.sql.MysqlConfig to use local.properties file data;
//        classes.sql.MysqlConfig config = new classes.sql.MysqlConfig();

        CommandsPerformer performer = new CommandsPerformer();
        performer.executeCLICommand(args);

//        try {
////            Connection connection = DriverManager
////                    .getConnection(
////                            config.getProtocol() + config.getHost() + config.getDatabase(),
////                            config.getUser(),
////                            config.getPassword()
////                    );
//            performer.executeCommand(args);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
