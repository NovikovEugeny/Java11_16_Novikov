package by.tc.online_pharmacy.service.util;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.service.exception.ValidatorException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validator {

    private final static String INVALID_SURNAME = "invalidSurname";
    private final static String INVALID_NAME = "invalidName";
    private final static String INVALID_PATRONYMIC = "invalidPatronymic";
    private final static String INVALID_MOBILE = "invalidMobile";
    private final static String INVALID_PASSWORD = "invalidPassword";
    private final static String INVALID_CONFIRM = "invalidConfirm";

    private final static String ENTER_MOBILE = "Enter mobile";
    private final static String ENTER_PASSWORD = "Enter password";

    private final static String INVALID_NAME_MESSAGE =
            "*At least 2 letters(first capital)";
    private final static String INVALID_MOBILE_MESSAGE =
            "*correct form: +375xxxxxxxxx";
    private final static String INVALID_PASSWORD_MESSAGE =
            "*At least 8 char.(one letter in each register and one digit)";
    private final static String INVALID_CONFIRM_MESSAGE =
            "passwords must be equals";

    private final static String INVALID_DRUG_NAME_MESSAGE =
            "*Incorrect drug name, at least 3 letters";


    private final static String MOBILE_PATTERN = "^\\+375\\d{9}$";
    private final static String PASSWORD_PATTERN = "(?=^.{8,}$)((?=.*\\d)|" +
            "(?=.*\\W+))(?![.\\n])(?=.*[A-ZА-Я])(?=.*[a-zа-я]).*$";
    private final static String NAME_PATTERN_RU = "^[А-Я][а-я]+$";
    private final static String NAME_PATTERN_EN = "^[A-Z][a-z]+$";
    private final static String DRUG_NAME_PATTERN_ru = "^[A-Za-z][a-z]{2,}$";
    private final static String DRUG_NAME_PATTERN_en = "^[А-Яа-я][а-я]{2,}$";

    private static boolean testPattern(String pattern, String str) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void signInValidate(String mobile, String password) throws ValidatorException {

        Map<String, String> errors = new HashMap<>();

        errors.put(INVALID_MOBILE, null);
        errors.put(INVALID_PASSWORD, null);

        if (mobile.isEmpty()) {
            errors.replace(INVALID_MOBILE, ENTER_MOBILE);
        }
        if (password.isEmpty()) {
            errors.replace(INVALID_PASSWORD, ENTER_PASSWORD);
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

        errors.put(INVALID_SURNAME, null);
        errors.put(INVALID_NAME, null);
        errors.put(INVALID_PATRONYMIC, null);
        errors.put(INVALID_MOBILE, null);
        errors.put(INVALID_PASSWORD, null);
        errors.put(INVALID_CONFIRM, null);

        if (!testPattern(NAME_PATTERN_EN, user.getSurname()) &&
                !testPattern(NAME_PATTERN_RU, user.getSurname())) {
            errors.replace(INVALID_SURNAME, INVALID_NAME_MESSAGE);
        }
        if (!testPattern(NAME_PATTERN_EN,user.getName()) &&
                !testPattern(NAME_PATTERN_RU, user.getName())) {
            errors.replace(INVALID_NAME, INVALID_NAME_MESSAGE);
        }
        if (!testPattern(NAME_PATTERN_EN,user.getPatronymic()) &&
                !testPattern(NAME_PATTERN_RU, user.getPatronymic())) {
            errors.replace(INVALID_PATRONYMIC, INVALID_NAME_MESSAGE);
        }
        if (!testPattern(MOBILE_PATTERN, user.getMobilePhone())) {
            errors.replace(INVALID_MOBILE, INVALID_MOBILE_MESSAGE);
        }
        if (!testPattern(PASSWORD_PATTERN, user.getPassword())) {
            errors.replace(INVALID_PASSWORD, INVALID_PASSWORD_MESSAGE);
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.replace(INVALID_CONFIRM, INVALID_CONFIRM_MESSAGE);
        }

        Collection<String> values = errors.values();

        for (String str : values) {
            if (str != null) {
                throw new ValidatorException(errors);
            }
        }
    }

    public static void searchValidate(String name) throws ValidatorException {
        if (!testPattern(DRUG_NAME_PATTERN_en, name) &&
                !testPattern(DRUG_NAME_PATTERN_ru, name)) {
            throw new ValidatorException(INVALID_DRUG_NAME_MESSAGE);
        }
    }

    public static void addNewDrugValidate(Drug drug) {

    }
}
