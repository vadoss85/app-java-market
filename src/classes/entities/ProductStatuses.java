package classes.entities;

import java.util.HashMap;
import java.util.Map;

public enum ProductStatuses {
    IN_STOCK("in_stock"),
    RUNNING_LOW("running_low"),
    OUT_OF_STOCK("out_of_stock");

    final public String status;

    ProductStatuses(String status) {
        this.status = status;
    }

    private static final Map<String, ProductStatuses> lookup = new HashMap<>();

    static
    {
        for(ProductStatuses st : ProductStatuses.values())
        {
            lookup.put(st.status, st);
        }
    }

    public static ProductStatuses get(String status)
    {
        return lookup.get(status);
    }
}
