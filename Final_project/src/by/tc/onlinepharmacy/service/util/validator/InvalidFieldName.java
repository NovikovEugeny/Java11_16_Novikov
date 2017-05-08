package by.tc.onlinepharmacy.service.util.validator;

/**
 * The class contains constants that representing the names of the invalid fields.
 */
final class InvalidFieldName {

    private InvalidFieldName() {
    }

    //authorization
    final static String INVALID_SURNAME = "invalidSurname";
    final static String INVALID_NAME = "invalidName";
    final static String INVALID_PATRONYMIC = "invalidPatronymic";
    final static String INVALID_MOBILE = "invalidMobile";
    final static String INVALID_PASSWORD = "invalidPassword";
    final static String INVALID_CONFIRM = "invalidConfirm";

    //add new drug
    final static String INVALID_DRUG_NAME = "invalidDrugName";
    final static String INVALID_DRUG_AMOUNT = "invalidDrugAmount";
    final static String INVALID_ACTIVE_SUBSTANCES = "invalidAS";
    final static String INVALID_COUNTRY = "invalidCountry";
    final static String INVALID_PRICE = "invalidPrice";
    final static String INVALID_QUANTITY = "invalidQuantity";

}