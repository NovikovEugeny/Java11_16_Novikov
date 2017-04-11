package by.tc.online_pharmacy.service.util.validator;


public class ErrorMessage {

    private ErrorMessage() {}

    //sign in
    public final static String EMPTY_MOBILE_MESSAGE = "Enter mobile";
    public final static String EMPTY_PASSWORD_MESSAGE = "Enter password";

    //sign up
    public final static String INVALID_NAME_MESSAGE =
            "*At least 2 letters(first capital)";

    public final static String INVALID_MOBILE_MESSAGE =
            "*correct form: +375xxxxxxxxx";

    public final static String INVALID_PASSWORD_MESSAGE =
            "*At least 8 char.(one letter in each register and one digit)";

    public final static String INVALID_CONFIRM_MESSAGE =
            "passwords must be equals";

    //search
    public final static String INVALID_FOR_SEARCH_DRUG_NAME_MESSAGE =
            "*Incorrect drug name, at least 3 letters";

    //add new drug
    public final static String INVALID_DRUG_NAME_MESSAGE =
            "*At least 3 letters(first capital)";

    public final static String INVALID_DRUG_AMOUNT_MESSAGE =
            "*correct form: 20 pills";

    public final static String INVALID_ACTIVE_SUBSTANCES_MESSAGE =
            "*correct form: substance - xx mg/g/ml..., ...";

    public final static String INVALID_COUNTRY_MESSAGE =
            "*At least 3 letters(first capital)";

    public final static String INVALID_PRICE_MESSAGE =
            "*Only integer or fractional number(not more than 999.99)";

}
