package classes.CommandsPerformer.performers;

public class ProductFindErrorException extends RuntimeException {
    public ProductFindErrorException(String productId) {
        super(productId);
    }
}
