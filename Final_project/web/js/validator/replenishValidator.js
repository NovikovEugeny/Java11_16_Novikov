var RU = "ru";

var CARD_NUMBER_PATTERN = /^\d{4}\s?\d{4}\s?\d{4}\s?\d{4}$/;
var MONEY_PATTERN = /^\d+(\.\d{1,2})?$/;

var CARD_ERROR_MESSAGE_EN = "*card number must contains 16 digits";
var CARD_ERROR_MESSAGE_RU = "*номер карты должен содержать 16 цифр";

var MONEY_ERROR_MESSAGE_RU = "*Только целое или дробное положительное число";
var MONEY_ERROR_MESSAGE_EN = "*Only integer or fractional positive number";

function validate() {

    document.getElementById("cardErr").innerHTML = "<br>";
    document.getElementById("moneyErr").innerHTML = "<br>";

    var isValid = true;

    var local = document.getElementById("local").getAttribute("data-item");
    var cardNumber = document.getElementById("card").value;
    var money = document.getElementById("money").value;

    if (!CARD_NUMBER_PATTERN.test(cardNumber)) {

        if (local == RU) {
            document.getElementById("cardErr").innerHTML = CARD_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("cardErr").innerHTML = CARD_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }

    if (!MONEY_PATTERN.test(money)) {

        if (local == RU) {
            document.getElementById("moneyErr").innerHTML = MONEY_ERROR_MESSAGE_RU;
        } else {
            document.getElementById("moneyErr").innerHTML = MONEY_ERROR_MESSAGE_EN;
        }

        isValid = false;
    }

    return isValid;
}