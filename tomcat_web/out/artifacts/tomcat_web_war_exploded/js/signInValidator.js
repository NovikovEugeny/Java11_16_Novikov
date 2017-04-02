function validateMobile() {
    var isValid = true;

    var pattern = /^\+375\d{9}$/;

    var mobile = document.getElementById("mobile").value;

    if (mobile == "") {
        document.getElementById("mobileErr").innerHTML = "*required";
        isValid = false;
    }

    if (isValid) {
        if (!pattern.test(mobile)) {
            document.getElementById("mobileErr").innerHTML = "*correct form: +375xxxxxxxxx";
            isValid = false;
        }
    }

    return isValid;
}


function validatePassword() {
    var isValid = true;

    var pattern = /(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-ZА-Я])(?=.*[a-zа-я]).*$/;

    var password = document.getElementById("password").value;

    if (password == "") {
        document.getElementById("passwordErr").innerHTML = "*required";
        isValid = false;
    }

    if (isValid) {
        if (!pattern.test(password)) {
            document.getElementById("passwordErr").innerHTML = "*At least" +
                " 8 char.(one letter in each register and one digit)";
            isValid = false;
        }
    }

    return isValid;
}

function validate() {
    var isValid = true;

    document.getElementById("mobileErr").innerHTML = "<br>";
    document.getElementById("passwordErr").innerHTML = "<br>";

    if (!validateMobile()) {
        isValid = false;
    }
    if (!validatePassword()) {
        isValid = false;
    }

    return isValid;
}