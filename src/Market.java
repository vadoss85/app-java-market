import classes.CommandsPerformer.CommandsPerformer;

public class Market {
    public static void main(String[] args) {
//        TODO change classes.sql.MysqlConfig to use local.properties file data;
//        classes.sql.MysqlConfig config = new classes.sql.MysqlConfig();

        CommandsPerformer performer = new CommandsPerformer();
        performer.executeCLICommand(args);

    }
}
