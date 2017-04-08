package by.tc.online_pharmacy.service.util;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.service.exception.ValidatorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Евгений on 02.04.2017.
 */
public class Validator {

    private final static String MOBILE_PATTERN = "^\\+375\\d{9}$";

    private final static String PASSWORD_PATTERN = "(?=^.{8,}$)((?=.*\\d)|" +
            "(?=.*\\W+))(?![.\\n])(?=.*[A-ZА-Я])(?=.*[a-zа-я]).*$";

    private final static String NAME_PATTERN_RU = "^[А-Я][а-я]{2,}$";
    private final static String NAME_PATTERN_EN = "^[A-Z][a-z]{2,}$";


    private static boolean testPattern(String pattern, String str) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void signInValidator(String mobile, String password) throws ValidatorException {
        if (mobile.isEmpty() && password.isEmpty()) {
            throw new ValidatorException("*Empty mobile phone and password");
        }
        if (mobile.isEmpty()) {
            throw new ValidatorException("*Empty mobile");
        }
        if (password.isEmpty()) {
            throw new ValidatorException("*Empty password");
        }
        if (!testPattern(MOBILE_PATTERN, mobile)) {
            throw new ValidatorException("*correct mobile form: +375xxxxxxxxx");
        }
        if (!testPattern(PASSWORD_PATTERN, password)) {
            throw new ValidatorException("*At least 8 char.in the password(one letter in each " +
                    "register and one digit)");
        }
    }

    public static void signUpValidator(User user) throws ValidatorException {

        final String EMPTY_FIELD_MESSAGE = "All fields must be filled";

        if (user.getSurname().isEmpty()) {
            throw new ValidatorException(EMPTY_FIELD_MESSAGE);
        }
        if (user.getName().isEmpty()) {
            throw new ValidatorException(EMPTY_FIELD_MESSAGE);
        }
        if (user.getPatronymic().isEmpty()) {
            throw new ValidatorException(EMPTY_FIELD_MESSAGE);
        }
        if (user.getMobilePhone().isEmpty()) {
            throw new ValidatorException(EMPTY_FIELD_MESSAGE);
        }
        if (user.getPassword().isEmpty()) {
            throw new ValidatorException(EMPTY_FIELD_MESSAGE);
        }
        if (user.getConfirmPassword().isEmpty()) {
            throw new ValidatorException(EMPTY_FIELD_MESSAGE);
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new ValidatorException("*The passwords are different");
        }
        if (!(testPattern(NAME_PATTERN_EN, user.getSurname()) ||
                testPattern(NAME_PATTERN_RU, user.getSurname()))) {
            throw new ValidatorException("*At least 2 letters in the surname" +
                    "(first capital)");
        }
        if (!(testPattern(NAME_PATTERN_EN, user.getName()) ||
                testPattern(NAME_PATTERN_RU, user.getName()))) {
            throw new ValidatorException("*At least 2 letters in the name" +
                    "(first capital)");
        }
        if (!(testPattern(NAME_PATTERN_EN, user.getPatronymic()) ||
                testPattern(NAME_PATTERN_RU, user.getPatronymic()))) {
            throw new ValidatorException("*At least 2 letters in the patronymic" +
                    "(first capital)");
        }
        if (!testPattern(MOBILE_PATTERN, user.getMobilePhone())) {
            throw new ValidatorException("*correct mobile form: +375xxxxxxxxx");
        }
        if (!testPattern(PASSWORD_PATTERN, user.getPassword())) {
            throw new ValidatorException("*At least 8 char.in the password(one " +
                    "letter in each register and one digit)");
        }
    }

    public static void addNewDrugValidator(Drug drug) {

    }
}
