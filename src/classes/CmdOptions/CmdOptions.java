package classes.CmdOptions;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CmdOptions {
    Options options = new Options();

    public CmdOptions() {
        this.generateOptions();
    }

    void generateOptions() {
        Option.Builder deleteProductOption = Option.builder(CmdCommands.DELETE_PRODUCT.command);
        Option.Builder showOrderOption = Option.builder(CmdCommands.SHOW_ORDER.command);

        deleteProductOption
                .longOpt("delete-product")
                .argName("productId")
                .hasArgs()
                .valueSeparator()
                .desc("Delete product");

        showOrderOption
                .longOpt("show-order")
                .argName("orderId")
                .hasArgs()
                .valueSeparator()
                .desc("Show order and related product data");

//        options.addOption(CmdCommands.CREATE_ORDER.command, false, "Test create order");
        options.addOption(CmdCommands.SHOW_PRODUCTS.command, "show-products",false, "Show products");
        options.addOption(CmdCommands.CREATE_PRODUCT.command, "create-product",false, "Create product");
        options.addOption(CmdCommands.UPDATE_PRODUCT.command, "update-product",false, "Update product");
        options.addOption(CmdCommands.DELETE_ALL_PRODUCTS.command, "delete-products",false, "Delete all product");
        options.addOption(deleteProductOption.build());
        options.addOption(CmdCommands.CREATE_ORDER.command, "create-order",false, "Create order");
        options.addOption(CmdCommands.SHOW_ORDERED_PRODUCTS.command, "show-ordered-products",false, "Show ordered products");
        options.addOption(showOrderOption.build());
        options.addOption(CmdCommands.LIST_ORDERS.command, "list-orders",false, "List orders");

    }

    public Options getOptions() {
        return options;
    }
}
