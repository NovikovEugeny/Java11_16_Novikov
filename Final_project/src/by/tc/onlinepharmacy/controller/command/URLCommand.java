package by.tc.onlinepharmacy.controller.command;

/**
 * The class contains constants that represent part of the URL for redirecting
 */
public final class URLCommand {

    private URLCommand() {
    }

    public final static String SHOW_DRUGS_TO_UPDATE = "controller?command=show_drugs_to_update&group=";
    public final static String SHOW_MESSAGES = "controller?command=show_messages";
    public final static String SHOW_RECIPE_EXTENSION_REQUESTS = "controller?command=show_recipe_extension_requests";
    public final static String PHARMACIST_SHOW_ORDER_LIST = "controller?command=pharmacist_show_order_list";
    public final static String CLIENT_SHOW_ORDER_LIST = "controller?command=client_show_order_list";
    public final static String ADD_DRUG_PAGE = "controller?command=add_drug_page";
    public final static String SHOW_DRUGS_TO_ORDER = "controller?command=show_drugs_to_order&group=";
    public final static String SHOW_ERECIPE = "controller?command=show_erecipe&recipeCode=";
    public final static String ORDER_BY_ERECIPE = "controller?command=order_by_erecipe_page";
    public final static String EXTEND_RECIPE_PAGE = "controller?command=extend_recipe_page";
    public final static String SHOW_BALANCE = "controller?command=show_balance";

}