package by.tc.online_pharmacy.service.util.validator;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.service.exception.ValidatorException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Validator {

    private final static String NOT_VALID = "notValid";
    private final static String GREATER_THAN_ZERO = "greaterThanZero";

    private static boolean test(String regExp, String str) {
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void validateSignIn(String mobile, String password) throws ValidatorException {

        Map<String, String> errors = new HashMap<>();

        errors.put(InvalidFieldName.INVALID_MOBILE, null);
        errors.put(InvalidFieldName.INVALID_PASSWORD, null);

        if (mobile == null || mobile.isEmpty()) {
            errors.replace(InvalidFieldName.INVALID_MOBILE, NOT_VALID);
        }
        if (password == null || password.isEmpty()) {
            errors.replace(InvalidFieldName.INVALID_PASSWORD, NOT_VALID);
        }

        Collection<String> values = errors.values();

        for (String str : values) {
            if (str != null) {
                throw new ValidatorException(errors);
            }
        }
    }

    public static void validateSignUp(User user) throws ValidatorException {

        Map<String, String> errors = new HashMap<>();

        errors.put(InvalidFieldName.INVALID_SURNAME, null);
        errors.put(InvalidFieldName.INVALID_NAME, null);
        errors.put(InvalidFieldName.INVALID_PATRONYMIC, null);
        errors.put(InvalidFieldName.INVALID_MOBILE, null);
        errors.put(InvalidFieldName.INVALID_PASSWORD, null);
        errors.put(InvalidFieldName.INVALID_CONFIRM, null);

        if (user.getSurname() == null || !test(RegExp.NAME_REG_EXP_EN, user.getSurname()) &&
                !test(RegExp.NAME_REG_EXP_RU, user.getSurname())) {
            errors.replace(InvalidFieldName.INVALID_SURNAME, NOT_VALID);
        }
        if (user.getName() == null || !test(RegExp.NAME_REG_EXP_EN, user.getName()) &&
                !test(RegExp.NAME_REG_EXP_RU, user.getName())) {
            errors.replace(InvalidFieldName.INVALID_NAME, NOT_VALID);
        }
        if (user.getPatronymic() == null || !test(RegExp.NAME_REG_EXP_EN, user.getPatronymic()) &&
                !test(RegExp.NAME_REG_EXP_RU, user.getPatronymic())) {
            errors.replace(InvalidFieldName.INVALID_PATRONYMIC, NOT_VALID);
        }
        if (user.getMobilePhone() == null || !test(RegExp.MOBILE_REG_EXP, user.getMobilePhone())) {
            errors.replace(InvalidFieldName.INVALID_MOBILE, NOT_VALID);
        }
        if (user.getPassword() == null || !test(RegExp.PASSWORD_REG_EXP, user.getPassword())) {
            errors.replace(InvalidFieldName.INVALID_PASSWORD, NOT_VALID);
        }
        if (user.getConfirmPassword() == null || !user.getPassword().equals(user.getConfirmPassword())) {
            errors.replace(InvalidFieldName.INVALID_CONFIRM, NOT_VALID);
        }

        Collection<String> values = errors.values();

        for (String str : values) {
            if (str != null) {
                throw new ValidatorException(errors);
            }
        }
    }

    public static void validateSearch(String name) throws ValidatorException {
        if (name == null || !test(RegExp.DRUG_NAME_FOR_SEARCH_REG_EXP_EN, name) &&
                !test(RegExp.DRUG_NAME_FOR_SEARCH_REG_EXP_RU, name)) {
            throw new ValidatorException();
        }
    }

    public static void validateNewDrugAddition(Drug drug) throws ValidatorException {

        Map<String, String> errors = new HashMap<>();

        errors.put(InvalidFieldName.INVALID_DRUG_NAME, null);
        errors.put(InvalidFieldName.INVALID_DRUG_AMOUNT, null);
        errors.put(InvalidFieldName.INVALID_ACTIVE_SUBSTANCES, null);
        errors.put(InvalidFieldName.INVALID_COUNTRY, null);
        errors.put(InvalidFieldName.INVALID_PRICE, null);

        if (drug.getName() == null || !test(RegExp.DRUG_NAME_REG_EXP_EN, drug.getName()) &&
                !test(RegExp.DRUG_NAME_REG_EXP_RU, drug.getName())) {
            errors.replace(InvalidFieldName.INVALID_DRUG_NAME, NOT_VALID);
        }
        if (drug.getDrugAmount() == null || !test(RegExp.DRUG_AMOUNT_REG_EXP_EN, drug.getDrugAmount()) &&
                !test(RegExp.DRUG_AMOUNT_REG_EXP_RU, drug.getDrugAmount())) {
            errors.replace(InvalidFieldName.INVALID_DRUG_AMOUNT, NOT_VALID);
        }
        if (drug.getActiveSubstances() == null ||
                !test(RegExp.ACTIVE_SUBSTANCES_REG_EXP_EN, drug.getActiveSubstances()) &&
                !test(RegExp.ACTIVE_SUBSTANCES_REG_EXP_RU, drug.getActiveSubstances())) {
            errors.replace(InvalidFieldName.INVALID_ACTIVE_SUBSTANCES, NOT_VALID);
        }
        if (drug.getCountry() == null || !test(RegExp.COUNTRY_REG_EXP_EN, drug.getCountry()) &&
                !test(RegExp.COUNTRY_REG_EXP_RU, drug.getCountry())) {
            errors.replace(InvalidFieldName.INVALID_COUNTRY, NOT_VALID);
        }
        if (!test(RegExp.PRICE_REG_EXP, Double.toString(drug.getPrice()))) {
            errors.replace(InvalidFieldName.INVALID_PRICE, NOT_VALID);
        }
        if (drug.getPrice() <= 0) {
            errors.replace(InvalidFieldName.INVALID_PRICE, GREATER_THAN_ZERO);
        }
        if (drug.getQuantity() <= 0) {
            errors.replace(InvalidFieldName.INVALID_QUANTITY, GREATER_THAN_ZERO);
        }

        Collection<String> values = errors.values();

        for (String str : values) {
            if (str != null) {
                throw new ValidatorException(errors);
            }
        }
    }

    public static void validateDrugQuantity(int quantity) throws ValidatorException {
        if (quantity <= 0) {
            throw new ValidatorException();
        }
    }

    public static void validateRecipeCode(String recipeCode) throws ValidatorException {
        if (recipeCode == null || recipeCode.isEmpty()) {
            throw new ValidatorException();
        }
    }

}