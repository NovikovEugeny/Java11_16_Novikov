package by.tc.online_pharmacy.service.util.validator;


final class RegExp {

    private RegExp() {
    }

    final static String MOBILE_REG_EXP = "^\\+375\\d{9}$";

    final static String PASSWORD_REG_EXP = "(?=^.{8,}$)((?=.*\\d)|" +
            "(?=.*\\W+))(?![.\\n])(?=.*[A-ZА-Я])(?=.*[a-zа-я]).*$";

    final static String NAME_REG_EXP_RU = "^[А-Я][а-я]+$";
    final static String NAME_REG_EXP_EN = "^[A-Z][a-z]+$";

    final static String DRUG_NAME_FOR_SEARCH_REG_EXP_RU = "^[A-Za-z][a-z]{2,}$";
    final static String DRUG_NAME_FOR_SEARCH_REG_EXP_EN = "^[А-Яа-я][а-я]{2,}$";

    final static String DRUG_NAME_REG_EXP_EN = "^[A-Z][a-z]{2,}$";
    final static String DRUG_NAME_REG_EXP_RU = "^[А-Я][а-я]{2,}$";

    final static String DRUG_AMOUNT_REG_EXP_RU = "^\\d{1,3}\\s[а-я]+$";
    final static String DRUG_AMOUNT_REG_EXP_EN = "^\\d{1,3}\\s[a-z]+$";

    final static String ACTIVE_SUBSTANCES_REG_EXP_RU =
            "^([А-Яа-я][а-я]+\\s?-\\s?\\d+\\s?[а-я/\\d%]+[,;]?\\s?)+$";
    final static String ACTIVE_SUBSTANCES_REG_EXP_EN =
            "^([A-Za-z][a-z]+\\s?-\\s?\\d+\\s?[a-z/\\d%]+[,;]?\\s?)+$";

    final static String COUNTRY_REG_EXP_RU = "^[А-Я][А-Яа-я]{2,}$";
    final static String COUNTRY_REG_EXP_EN = "^[A-Z][A-Za-z]{2,}$";

    final static String PRICE_REG_EXP = "^\\d{1,3}(\\.\\d{1,2})?$";
}