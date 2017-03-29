function validateFullName() {
    var isValid = true;

    var pattern_en = /^[A-Z][a-z]{2,}$/;
    var pattern_ru = /^[А-Я][а-я]{2,}$/;
    var errorMessage = "*At least 3 characters(first capital)";

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

    var pattern = /^\+?[\d-\s()]+$/;

    var mobile = document.getElementById("mobile").value;

    if (!pattern.test(mobile)) {
        document.getElementById("mobileErr").innerHTML = "*valid characters: +- ()0-9";
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

    if (!pattern.test(confirm)) {
        document.getElementById("confirmErr").innerHTML = "*At least" +
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
        document.getElementById("confirmErr").innerHTML = "passwords must be equals";
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