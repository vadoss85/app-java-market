package classes.CmdOptions;

public enum CmdCommands {
    SHOW_PRODUCTS("sp"),
    CREATE_PRODUCT("cp"),
    UPDATE_PRODUCT("up"),
    DELETE_PRODUCT("dp"),
    DELETE_ALL_PRODUCTS("dap"),
    CREATE_ORDER("co"),
    SHOW_ORDERED_PRODUCTS("sop"),
    SHOW_ORDER("so"),
    LIST_ORDERS("lo");

    public String command;

    CmdCommands(String command) {
        this.command = command;
    }
}
