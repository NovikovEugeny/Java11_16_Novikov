function validateMobile() {
    var isValid = true;

    var mobile = document.getElementById("mobile").value;

    if (mobile == "") {
        document.getElementById("mobileErr").innerHTML = "Enter mobile";
        isValid = false;
    }

    return isValid;
}


function validatePassword() {
    var isValid = true;

    var password = document.getElementById("password").value;

    if (password == "") {
        document.getElementById("passwordErr").innerHTML = "Enter password";
        isValid = false;
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