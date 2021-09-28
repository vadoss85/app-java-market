package classes.CommandsPerformer.performers;

import java.sql.SQLException;

public class OrderFindErrorException extends RuntimeException {
    public OrderFindErrorException(String orderId) { super(orderId); }
}
