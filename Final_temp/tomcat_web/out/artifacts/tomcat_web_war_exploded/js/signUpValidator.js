function validateFullName() {
    var isValid = true;

    var pattern_en = /^[A-Z][a-z]+$/;
    var pattern_ru = /^[А-Я][а-я]+$/;
    var errorMessage = "*At least 2 letters(first capital)";

    var surname = document.getElementById("surname").value;
    var name = document.getElementById("name").value;
    var patronymic = document.getElementById("patronymic").value;

    if (!(pattern_en.test(surname) || pattern_ru.test(surname))) {
        document.getElementById("surnameErr").innerHTML = errorMessage;
        isValid = false;
    }
    if (!(pattern_en.test(name) || pattern_ru.test(name))) {
        document.getElementById("nameErr").innerHTML = errorMessage;
        isValid = false;
    }
    if (!(pattern_en.test(patronymic) || pattern_ru.test(patronymic))) {
        document.getElementById("patronymicErr").innerHTML = errorMessage;
        isValid = false;
    }

    return isValid;
}


function validateMobile() {
    var isValid = true;

    var pattern = /^\+375\d{9}$/;

    var mobile = document.getElementById("mobile").value;

    if (!pattern.test(mobile)) {
        document.getElementById("mobileErr").innerHTML = "*correct form: +375xxxxxxxxx";
        isValid = false;
    }

    return isValid;
}


function validatePassword() {
    var isValid = true;

    var pattern = /(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-ZА-Я])(?=.*[a-zа-я]).*$/;

    var password = document.getElementById("password").value;
    var confirm = document.getElementById("confirm").value;

    if (!pattern.test(password)) {
        document.getElementById("passwordErr").innerHTML = "*At least" +
            " 8 char.(one letter in each register and one digit)";
        isValid = false;
    }

    return isValid;
}


function isEquals() {
    var isEquals = true;

    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm").value;

    if (password != confirmPassword) {
        document.getElementById("confirmErr").innerHTML =
            "*passwords must be equals";
        isEquals = false;
    }

    return isEquals;
}

function validate() {
    var isValid = true;

    document.getElementById("surnameErr").innerHTML = "";
    document.getElementById("nameErr").innerHTML = "";
    document.getElementById("patronymicErr").innerHTML = "";
    document.getElementById("mobileErr").innerHTML = "";
    document.getElementById("passwordErr").innerHTML = "";
    document.getElementById("confirmErr").innerHTML = "";

    if (!validateFullName()) {
        isValid = false;
    }
    if (!validateMobile()) {
        isValid = false;
    }
    if (!validatePassword()) {
        isValid = false;
    }
    if (!isEquals()) {
        isValid = false;
    }

    return isValid;
}