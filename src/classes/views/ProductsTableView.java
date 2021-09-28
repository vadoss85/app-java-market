package classes.views;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductsTableView {
    public static void printPreviewTable(String name, String price, String status) {
        String leftAlignFormat = "| %-15s | %-5s | %-15s |%n";

        System.out.format("+-----------------+-------+-----------------+%n");
        System.out.format("| Product name    | Price | Status          |%n");
        System.out.format("+-----------------+-------+-----------------+%n");
        System.out.format(leftAlignFormat, name , price, status);
        System.out.format("+-----------------+-------+-----------------+%n");
    }
    public static void printPreviewTable(ArrayList<HashMap> products) {
        String leftAlignFormat = "| %-15s | %-5s | %-15s |%n";

        System.out.format("+-----------------+-------+-----------------+%n");
        System.out.format("| Product name    | Price | Status          |%n");
        System.out.format("+-----------------+-------+-----------------+%n");
        for (int i = 0; i < products.size(); i++) {
            final HashMap product = products.get(i);
            System.out.format(leftAlignFormat, product.get("name") , product.get("price"), product.get("status"));
        }
        System.out.format("+-----------------+-------+-----------------+%n");
    }

    public static void printFullTable(ArrayList<HashMap> products) {
        String leftAlignFormat = "%-3d | %-15s | %-5s | %-15s | %-15s |%n";

        System.out.format("+---+-----------------+-------+-----------------+---------------------+%n");
        System.out.format(" id | Product name    | Price | Status          | Crated date         |%n");
        System.out.format("+---+-----------------+-------+-----------------+---------------------+%n");
        for (int i = 0; i < products.size(); i++) {
            final HashMap product = products.get(i);
            System.out.format(
                    leftAlignFormat,
                    product.get("id"),
                    product.get("name"),
                    product.get("price"),
                    product.get("status"),
                    product.get("created_at"));
        }
        System.out.format("+---+-----------------+-------+-----------------+---------------------+%n");
    }
}