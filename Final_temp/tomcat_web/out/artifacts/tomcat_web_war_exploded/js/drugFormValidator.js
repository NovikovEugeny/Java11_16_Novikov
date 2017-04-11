function validateName() {
    var isValid = true;

    var pattern_en = /^[A-Z][a-z]{2,}$/;
    var pattern_ru = /^[А-Я][а-я]{2,}$/;
    var errorMessage = "*At least 3 letters(first capital)";

    var name = document.getElementById("name").value;

    if (!(pattern_en.test(name) || pattern_ru.test(name))) {
        document.getElementById("nameErr").innerHTML = errorMessage;
        isValid = false;
    }

    return isValid;
}

function validateAmount() {
    var isValid = true;

    var pattern_en = /^\d{1,3}\s[a-z]+$/;
    var pattern_ru = /^\d{1,3}\s[а-я]+$/;
    var errorMessage = "*correct form: 20 pills";

    var amount = document.getElementById("amount").value;

    if (!(pattern_en.test(amount) || pattern_ru.test(amount))) {
        document.getElementById("amountErr").innerHTML = errorMessage;
        isValid = false;
    }

    return isValid;
}

function validateActiveSubstances() {
    var isValid = true;

    var pattern_en = /^([A-Za-z][a-z]+\s?-\s?\d+\s?[a-z/]+[,;]?\s?)+$/;
    var pattern_ru = /^([А-Яа-я][а-я]+\s?-\s?\d+\s?[а-я/]+[,;]?\s?)+$/;
    var errorMessage = "*correct form: substance - xx mg/g/ml..., ...";

    var aS = document.getElementById("as").value;

    if (!(pattern_en.test(aS) || pattern_ru.test(aS))) {
        document.getElementById("activeSubstancesErr").innerHTML = errorMessage;
        isValid = false;
    }

    return isValid;
}

function validateCountry() {
    var isValid = true;

    var pattern_en = /^[A-Z][A-Za-z]{2,}$/;
    var pattern_ru = /^[А-Я][А-Яа-я]{2,}$/;
    var errorMessage = "*At least 3 letters(first capital)";

    var country = document.getElementById("country").value;

    if (!(pattern_en.test(country) || pattern_ru.test(country))) {
        document.getElementById("countryErr").innerHTML = errorMessage;
        isValid = false;
    }

    return isValid;
}

function validatePrice() {
    var isValid = true;

    var pattern = /^\d{1,3}(\.\d{1,2})?$/;
    var errorMessage = "*Only integer or fractional number(not more than" +
        " 999.99)";

    var price = document.getElementById("price").value;

    if (!(pattern.test(price))) {
        document.getElementById("priceErr").innerHTML = errorMessage;
        isValid = false;
    }

    return isValid;
}

function validateQuantity() {
    var isValid = true;

    var errorMessage = "*required";

    var quantity = document.getElementById("quantity").value;

    if (quantity == "") {
        document.getElementById("quantityErr").innerHTML = errorMessage;
        isValid = false;
    }

    return isValid;
}

function validate() {
    var isValid = true;

    document.getElementById("nameErr").innerHTML = "";
    document.getElementById("amountErr").innerHTML = "";
    document.getElementById("activeSubstancesErr").innerHTML = "";
    document.getElementById("countryErr").innerHTML = "";
    document.getElementById("priceErr").innerHTML = "";
    document.getElementById("quantityErr").innerHTML = "";

    if (!validateName()) {
        isValid = false;
    }
    if (!validateAmount()) {
        isValid = false;
    }
    if (!validateActiveSubstances()) {
        isValid = false;
    }
    if (!validateCountry()) {
        isValid = false;
    }
    if (!validatePrice()) {
        isValid = false;
    }
    if (!validateQuantity()) {
        isValid = false;
    }

    return isValid;
}