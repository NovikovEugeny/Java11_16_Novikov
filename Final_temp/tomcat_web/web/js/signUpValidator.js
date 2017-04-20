var RU = "ru";

var NAME_PATTERN_EN = /^[A-Z][a-z]+$/;
var NAME_PATTERN_RU = /^[А-Я][а-я]+$/;

var MOBILE_PATTERN = /^\+375\d{9}$/;
var PASSWORD_PATTERN = /(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-ZА-Я])(?=.*[a-zа-я]).*$/;

var NAME_ERROR_MESSAGE_EN = "*At least 2 letters(first capital)";
var NAME_ERROR_MESSAGE_RU = "*Минимум 2 буквы(первая заглавная)";

var MOBILE_ERROR_MESSAGE_RU = "*Правильная форма: +375xxxxxxxxx";
var MOBILE_ERROR_MESSAGE_EN = "*Сorrect form: +375xxxxxxxxx";

var PASSWORD_ERROR_MESSAGE_RU = "*Минимум 8 символов(1 цифра и по 1 букве в каждом регистре)";
var PASSWORD_ERROR_MESSAGE_EN = "*At least 8 char.(one letter in each register and one digit)";

var CONFIRM_ERROR_MESSAGE_RU = "*Пароли должны совпадать";
var CONFIRM_ERROR_MESSAGE_EN = "*passwords must be equals";


function validateFullName(local) {
    var isValid = true;

    var surname = document.getElementById("surname").value;
    var name = document.getElementById("name").value;
    var patronymic = document.getElementById("patronymic").value;

    if (!(NAME_PATTERN_EN.test(surname) || NAME_PATTERN_RU.test(surname))) {
        if (local == RU) {
            document.getElementById("surnameErr").innerHTML = NAME_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("surnameErr").innerHTML = NAME_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }
    if (!(NAME_PATTERN_EN.test(name) || NAME_PATTERN_RU.test(name))) {
        if (local == RU) {
            document.getElementById("nameErr").innerHTML = NAME_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("nameErr").innerHTML = NAME_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }
    if (!(NAME_PATTERN_EN.test(patronymic) || NAME_PATTERN_RU.test(patronymic))) {
        if (local == RU) {
            document.getElementById("patronymicErr").innerHTML = NAME_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("patronymicErr").innerHTML = NAME_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }

    return isValid;
}


function validateMobile(local) {
    var isValid = true;

    var mobile = document.getElementById("mobile").value;

    if (!MOBILE_PATTERN.test(mobile)) {
        if (local == RU) {
            document.getElementById("mobileErr").innerHTML = MOBILE_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("mobileErr").innerHTML = MOBILE_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }

    return isValid;
}


function validatePassword(local) {
    var isValid = true;

    var password = document.getElementById("password").value;

    if (!PASSWORD_PATTERN.test(password)) {
        if (local == RU) {
            document.getElementById("passwordErr").innerHTML = PASSWORD_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("passwordErr").innerHTML = PASSWORD_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }

    return isValid;
}


function validateConfirm(local) {
    var isValid = true;

    var confirm = document.getElementById("confirm").value;

    if (!PASSWORD_PATTERN.test(confirm)) {

        if (local == RU) {
            document.getElementById("confirmErr").innerHTML = PASSWORD_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("confirmErr").innerHTML = PASSWORD_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }

    return isValid;
}


function isEquals(local) {
    var isEquals = true;

    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm").value;

    if (password != confirmPassword) {
        if (local == RU) {
            document.getElementById("confirmErr").innerHTML = CONFIRM_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("confirmErr").innerHTML = CONFIRM_ERROR_MESSAGE_EN;
        }

        isEquals = false;
    }

    return isEquals;
}

function validate() {
    var isValid = true;

    document.getElementById("surnameErr").innerHTML = "<br>";
    document.getElementById("nameErr").innerHTML = "<br>";
    document.getElementById("patronymicErr").innerHTML = "<br>";
    document.getElementById("mobileErr").innerHTML = "<br>";
    document.getElementById("passwordErr").innerHTML = "<br>";
    document.getElementById("confirmErr").innerHTML = "<br>";

    var local = document.getElementById("local").getAttribute("data-item");

    if (!validateFullName(local)) {
        isValid = false;
    }
    if (!validateMobile(local)) {
        isValid = false;
    }
    if (!validatePassword(local)) {
        isValid = false;
    }
    if (!validateConfirm(local)) {
        isValid = false;
    }
    if (!isEquals(local)) {
        isValid = false;
    }

    return isValid;
}