var RU = "ru";

var NAME_PATTERN_RU = /^[А-Я][а-я\s]{2,}$/;
var NAME_PATTERN_EN = /^[A-Z][a-z\s]{2,}$/;

var AMOUNT_PATTERN_EN = /^\d{1,4}\s[a-z]+$/;
var AMOUNT_PATTERN_RU = /^\d{1,4}\s[а-я]+$/;

var ACTICE_SUBSTANCES_PATTERN_EN = /^([A-Za-z][a-z]+\s?-\s?\d+\s?[a-z/\d%]+[,;]?\s?)+$/;
var ACTICE_SUBSTANCES_PATTERN_RU = /^([А-Яа-я][а-я]+\s?-\s?\d+\s?[а-я/\d%]+[,;]?\s?)+$/;

var COUNTRY_PATTERN_EN = /^[A-Z][A-Za-z]{2,}$/;
var COUNTRY_PATTERN_RU = /^[А-Я][А-Яа-я]{2,}$/;

var PRICE_PATTERN = /^\d{1,3}(\.\d{1,2})?$/;

var NAME_ERROR_MESSAGE_EN = "*At least 3 letters(first capital)";
var NAME_ERROR_MESSAGE_RU = "*Минимум 3 буквы(первая заглавная)";

var AMOUNT_ERROR_MESSAGE_EN = "*Correct form: 20 pills(example)";
var AMOUNT_ERROR_MESSAGE_RU = "*Правильная форма: 20 таблеток(пример)";

var ACTIVE_SUBSTANCES_ERROR_MESSAGE_EN = "*Correct form: substance - xx mg/g/ml..., ...";
var ACTIVE_SUBSTANCES_ERROR_MESSAGE_RU = "*Правильная форма: вещ-во - хх мг/г/мл..., ...";

var COUNTRY_ERROR_MESSAGE_EN = "*At least 3 letters(first capital)";
var COUNTRY_ERROR_MESSAGE_RU = "*Минимум 3 буквы(первая заглавная)";

var PRICE_ERROR_MESSAGE_EN = "*Only integer or fractional number(not more than 999.99)";
var PRICE_ERROR_MESSAGE_RU = "*Только целое или дробное число(не более 999.99)";

var PRICE_NEGATIVE_ERROR_EN = "*Only more than zero";
var PRICE_NEGATIVE_ERROR_RU = "*Строго больше нуля";

var QUANTITY_ERROR_MESSAGE_EN = "*Only integer positive value";
var QUANTITY_ERROR_MESSAGE_RU = "*Только целое положительное число";


function validateName(locale) {
    var isValid = true;

    var name = document.getElementById("name").value;

    if (!(NAME_PATTERN_EN.test(name) || NAME_PATTERN_RU.test(name))) {
        if (locale == RU) {
            document.getElementById("nameErr").innerHTML = NAME_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("nameErr").innerHTML = NAME_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }

    return isValid;
}

function validateAmount(locale) {
    var isValid = true;

    var amount = document.getElementById("amount").value;

    if (!(AMOUNT_PATTERN_EN.test(amount) || AMOUNT_PATTERN_RU.test(amount))) {
        if (locale == RU) {
            document.getElementById("amountErr").innerHTML = AMOUNT_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("amountErr").innerHTML = AMOUNT_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }

    return isValid;
}

function validateActiveSubstances(locale) {
    var isValid = true;

    var aS = document.getElementById("as").value;

    if (!(ACTICE_SUBSTANCES_PATTERN_EN.test(aS) || ACTICE_SUBSTANCES_PATTERN_RU.test(aS))) {
        if (locale == RU) {
            document.getElementById("activeSubstancesErr").innerHTML = ACTIVE_SUBSTANCES_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("activeSubstancesErr").innerHTML = ACTIVE_SUBSTANCES_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }

    return isValid;
}

function validateCountry(locale) {
    var isValid = true;

    var country = document.getElementById("country").value;

    if (!(COUNTRY_PATTERN_EN.test(country) || COUNTRY_PATTERN_RU.test(country))) {
        if (locale == RU) {
            document.getElementById("countryErr").innerHTML = COUNTRY_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("countryErr").innerHTML = COUNTRY_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }

    return isValid;
}

function validatePrice(locale) {
    var isValid = true;

    var price = document.getElementById("price").value;

    if (!(PRICE_PATTERN.test(price))) {
        if (locale == RU) {
            document.getElementById("priceErr").innerHTML = PRICE_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("priceErr").innerHTML = PRICE_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }

    if (price <= 0) {
        if (locale == RU) {
            document.getElementById("priceErr").innerHTML = PRICE_NEGATIVE_ERROR_RU;
        } else {
            document.getElementById("priceErr").innerHTML = PRICE_NEGATIVE_ERROR_EN;
        }

        isValid = false;
    }

    return isValid;
}

function validateQuantity(locale) {
    var isValid = true;

    var quantity = document.getElementById("quantity").value;

    if (quantity < 1) {
        if (locale == RU) {
            document.getElementById("quantityErr").innerHTML = QUANTITY_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("quantityErr").innerHTML = QUANTITY_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }

    return isValid;
}

function validate() {
    var isValid = true;

    document.getElementById("nameErr").innerHTML = "<br>";
    document.getElementById("amountErr").innerHTML = "<br>";
    document.getElementById("activeSubstancesErr").innerHTML = "<br>";
    document.getElementById("countryErr").innerHTML = "<br>";
    document.getElementById("priceErr").innerHTML = "<br>";
    document.getElementById("quantityErr").innerHTML = "<br>";

    var local = document.getElementById("local").getAttribute("data-item");

    if (!validateName(local)) {
        isValid = false;
    }
    if (!validateAmount(local)) {
        isValid = false;
    }
    if (!validateActiveSubstances(local)) {
        isValid = false;
    }
    if (!validateCountry(local)) {
        isValid = false;
    }
    if (!validatePrice(local)) {
        isValid = false;
    }
    if (!validateQuantity(local)) {
        isValid = false;
    }

    return isValid;
}