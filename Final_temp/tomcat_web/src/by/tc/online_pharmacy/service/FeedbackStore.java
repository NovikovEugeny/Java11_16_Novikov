package by.tc.online_pharmacy.service;


public class FeedbackStore {

    private FeedbackStore() {}

    public final static String SUCCESSFUL_EXECUTED_ORDER_MESSAGE =
            "The order was executed successfully.";

    public final static String RECIPE_EXPIRED_MESSAGE =
            "The recipe has expired, send the renewal request.";

    public final static String NOT_ENOUGH_MONEY_MESSAGE =
            "There is not enough money on the account, replenish the balance.";

    public final static String NOT_ENOUGH_DRUG_MESSAGE =
            "It is not enough goods in stock.";
}
