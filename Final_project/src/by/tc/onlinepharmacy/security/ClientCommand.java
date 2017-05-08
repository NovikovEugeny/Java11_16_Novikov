package by.tc.onlinepharmacy.security;

/**
 * An enumeration that contains a list of all available commands for client.
 */
public enum ClientCommand {
    SIGN_UP,
    ORDER_WITHOUT_RECIPE,
    ORDER_WITH_RECIPE,
    SHOW_DRUGS_TO_ORDER,
    SHOW_ERECIPE,
    CLIENT_SHOW_ORDER_LIST,
    CLIENT_CANCEL_ORDER,
    SEND_RECIPE_EXTENSION_REQUEST,
    REPORT_ABOUT_DELIVERY,
    SHOW_MESSAGES,
    DELETE_MESSAGE,
    SHOW_BALANCE,
    SHOW_SHOPPING_LIST,
    GROUPS_TO_ORDER_PAGE,
    ORDER_BY_ERECIPE_PAGE,
    EXTEND_RECIPE_PAGE,
    REPLENISH_BALANCE
}