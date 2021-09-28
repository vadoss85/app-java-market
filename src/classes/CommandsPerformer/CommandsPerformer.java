package classes.CommandsPerformer;

import classes.CmdOptions.CmdCommands;
import classes.CmdOptions.CmdOptions;
import classes.CommandsPerformer.performers.*;
import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandsPerformer {
    CommandLineParser parser;
    CmdOptions options;

    public CommandsPerformer() {
        options = new CmdOptions();
        parser = new DefaultParser();
    }

    CommandLine parse(String[] args) throws ParseException {
        return this.parser.parse(this.options.getOptions(), args);
    }

    public void executeCLICommand(String[] args) {
        try {
            CommandLine cmd = this.parse(args);

            if (Arrays.stream(args).count() == 0) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp( "java -jar ./Java.Market.jar", this.options.getOptions() );
                return;
            }

            if(cmd.hasOption(CmdCommands.CREATE_PRODUCT.command)) {
                BaseCommandsPerformer productPerformer = new CreateProductCommandsPerformer();
                productPerformer.execute();
            }

            if(cmd.hasOption(CmdCommands.SHOW_PRODUCTS.command)) {
                BaseCommandsPerformer productPerformer = new ShowProductsPerformer();
                productPerformer.execute();
            }

            if(cmd.hasOption(CmdCommands.DELETE_ALL_PRODUCTS.command)) {
                BaseCommandsPerformer productPerformer = new DeleteProductsCommandsPerformer();
                productPerformer.execute();
            }

            if(cmd.hasOption(CmdCommands.DELETE_PRODUCT.command)) {
                final Map params = new HashMap(){{
                    put("productId", cmd.getOptionValue(CmdCommands.DELETE_PRODUCT.command));
                }};
                System.out.println(params.get("productId"));
                BaseCommandsPerformer productPerformer = new DeleteProductsCommandsPerformer();
                productPerformer.execute(params);
            }

            if(cmd.hasOption(CmdCommands.CREATE_ORDER.command)) {
                BaseCommandsPerformer orderPerformer = new CreateOrderCommandsPerformer();
                orderPerformer.execute();
            }

            if(cmd.hasOption(CmdCommands.SHOW_ORDERED_PRODUCTS.command)) {
                BaseCommandsPerformer orderPerformer = new ShowOrderedProductsCommandsPerformer();
                orderPerformer.execute();
            }

            if(cmd.hasOption(CmdCommands.LIST_ORDERS.command)) {
                BaseCommandsPerformer orderPerformer = new ListOrdersCommandsPerformer();
                orderPerformer.execute();
            }

            if(cmd.hasOption(CmdCommands.SHOW_ORDER.command)) {
                System.out.println(cmd.getOptionValue(CmdCommands.SHOW_ORDER.command));
                final Map params = new HashMap(){{
                    put("orderId", cmd.getOptionValue(CmdCommands.SHOW_ORDER.command));
                }};
                BaseCommandsPerformer orderPerformer = new ListOrdersCommandsPerformer();
                orderPerformer.execute(params);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
