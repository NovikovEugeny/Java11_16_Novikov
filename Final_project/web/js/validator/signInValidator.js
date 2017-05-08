var RU = "ru";

var MOBILE_ERROR_MESSAGE_RU = "*Введите моб. телефон";
var MOBILE_ERROR_MESSAGE_EN = "*Enter mobile";
var PASSWORD_ERROR_MESSAGE_RU = "*Введите пароль";
var PASSWORD_ERROR_MESSAGE_EN = "*Enter password";

function validateMobile(local) {
    var isValid = true;

    var mobile = document.getElementById("mobile").value;

    if (mobile == "") {
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

    if (password == "") {
        if (local == RU) {
            document.getElementById("passwordErr").innerHTML = PASSWORD_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("passwordErr").innerHTML = PASSWORD_ERROR_MESSAGE_EN;
        }
        isValid = false;
    }

    return isValid;
}

function validate() {
    var isValid = true;

    document.getElementById("mobileErr").innerHTML = "<br>";
    document.getElementById("passwordErr").innerHTML = "<br>";

    var local = document.getElementById("local").getAttribute("data-item");

    if (!validateMobile(local)) {
        isValid = false;
    }
    if (!validatePassword(local)) {
        isValid = false;
    }

    return isValid;
}