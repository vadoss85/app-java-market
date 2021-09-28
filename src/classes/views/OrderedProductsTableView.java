package classes.views;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderedProductsTableView {
    public static void printTable(ArrayList<HashMap> products) {
        String leftAlignFormat = "| %-15s | %-14s |%n";

        System.out.format("+-----------------+----------------+%n");
        System.out.format("| Product name    | Total quantity |%n");
        System.out.format("+-----------------+----------------+%n");
        for (int i = 0; i < products.size(); i++) {
            final HashMap product = products.get(i);
            System.out.format(leftAlignFormat, product.get("name") , product.get("total"));
        }
        System.out.format("+-----------------+----------------+%n");
    }

    public static void printOrderProductDataTable(ArrayList<HashMap> orders) {
        String leftAlignFormat = "| %-8d | %-11d | %-13s| %-14d | %-19s |%n";

        System.out.format("+----------+-------------+--------------+----------------+---------------------+%n");
        System.out.format("| Order ID | Total price | Product name | Total quantity | Order created date  |%n");
        System.out.format("+----------+-------------+--------------+----------------+---------------------+%n");
        for (int i = 0; i < orders.size(); i++) {
            final HashMap product = orders.get(i);
            System.out.format(
                    leftAlignFormat,
                    product.get("id"),
                    product.get("price"),
                    product.get("name"),
                    product.get("quantity"),
                    product.get("createdAt")
            );
        }
        System.out.format("+----------+-------------+--------------+----------------+---------------------+%n");
    }
}
