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

    private static boolean test(String regExp, String str) {
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void signInValidate(String mobile, String password) throws ValidatorException {

        Map<String, String> errors = new HashMap<>();

        errors.put(InvalidFieldName.INVALID_MOBILE, null);
        errors.put(InvalidFieldName.INVALID_PASSWORD, null);

        if (mobile.isEmpty()) {
            errors.replace(InvalidFieldName.INVALID_MOBILE, NOT_VALID);
        }
        if (password.isEmpty()) {
            errors.replace(InvalidFieldName.INVALID_PASSWORD, NOT_VALID);
        }

        Collection<String> values = errors.values();

        for (String str : values) {
            if (str != null) {
                throw new ValidatorException(errors);
            }
        }
    }

    public static void signUpValidate(User user) throws ValidatorException {

        Map<String, String> errors = new HashMap<>();


        errors.put(InvalidFieldName.INVALID_SURNAME, null);
        errors.put(InvalidFieldName.INVALID_NAME, null);
        errors.put(InvalidFieldName.INVALID_PATRONYMIC, null);
        errors.put(InvalidFieldName.INVALID_MOBILE, null);
        errors.put(InvalidFieldName.INVALID_PASSWORD, null);
        errors.put(InvalidFieldName.INVALID_CONFIRM, null);

        if (!test(RegExp.NAME_REG_EXP_EN, user.getSurname()) &&
                !test(RegExp.NAME_REG_EXP_RU, user.getSurname())) {
            errors.replace(InvalidFieldName.INVALID_SURNAME, NOT_VALID);
        }
        if (!test(RegExp.NAME_REG_EXP_EN, user.getName()) &&
                !test(RegExp.NAME_REG_EXP_RU, user.getName())) {
            errors.replace(InvalidFieldName.INVALID_NAME, NOT_VALID);
        }
        if (!test(RegExp.NAME_REG_EXP_EN, user.getPatronymic()) &&
                !test(RegExp.NAME_REG_EXP_RU, user.getPatronymic())) {
            errors.replace(InvalidFieldName.INVALID_PATRONYMIC, NOT_VALID);
        }
        if (!test(RegExp.MOBILE_REG_EXP, user.getMobilePhone())) {
            errors.replace(InvalidFieldName.INVALID_MOBILE, NOT_VALID);
        }
        if (!test(RegExp.PASSWORD_REG_EXP, user.getPassword())) {
            errors.replace(InvalidFieldName.INVALID_PASSWORD, NOT_VALID);
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.replace(InvalidFieldName.INVALID_CONFIRM, NOT_VALID);
        }

        Collection<String> values = errors.values();

        for (String str : values) {
            if (str != null) {
                throw new ValidatorException(errors);
            }
        }
    }

    public static void searchValidate(String name) throws ValidatorException {
        if (!test(RegExp.DRUG_NAME_FOR_SEARCH_REG_EXP_EN, name) &&
                !test(RegExp.DRUG_NAME_FOR_SEARCH_REG_EXP_RU, name)) {
            throw new ValidatorException();
        }
    }

    public static void addNewDrugValidate(Drug drug) throws ValidatorException {

        Map<String, String> errors = new HashMap<>();

        errors.put(InvalidFieldName.INVALID_DRUG_NAME, null);
        errors.put(InvalidFieldName.INVALID_DRUG_AMOUNT, null);
        errors.put(InvalidFieldName.INVALID_ACTIVE_SUBSTANCES, null);
        errors.put(InvalidFieldName.INVALID_COUNTRY, null);
        errors.put(InvalidFieldName.INVALID_PRICE, null);

        if (!test(RegExp.DRUG_NAME_REG_EXP_EN, drug.getName()) &&
                !test(RegExp.DRUG_NAME_REG_EXP_RU, drug.getName())) {
            errors.replace(InvalidFieldName.INVALID_DRUG_NAME, NOT_VALID);
        }
        if (!test(RegExp.DRUG_AMOUNT_REG_EXP_EN, drug.getDrugAmount()) &&
                !test(RegExp.DRUG_AMOUNT_REG_EXP_RU, drug.getDrugAmount())) {
            errors.replace(InvalidFieldName.INVALID_DRUG_AMOUNT, NOT_VALID);
        }
        if (!test(RegExp.ACTIVE_SUBSTANCES_REG_EXP_EN, drug.getActiveSubstances()) &&
                !test(RegExp.ACTIVE_SUBSTANCES_REG_EXP_RU, drug.getActiveSubstances())) {
            errors.replace(InvalidFieldName.INVALID_ACTIVE_SUBSTANCES, NOT_VALID);
        }
        if (!test(RegExp.COUNTRY_REG_EXP_EN, drug.getCountry()) &&
                !test(RegExp.COUNTRY_REG_EXP_RU, drug.getCountry())) {
            errors.replace(InvalidFieldName.INVALID_COUNTRY, NOT_VALID);
        }
        if (!test(RegExp.PRICE_REG_EXP, Double.toString(drug.getPrice()))) {
            errors.replace(InvalidFieldName.INVALID_PRICE, NOT_VALID);
        }

        Collection<String> values = errors.values();

        for (String str : values) {
            if (str != null) {
                throw new ValidatorException(errors);
            }
        }
    }
}