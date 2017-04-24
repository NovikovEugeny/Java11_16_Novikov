function validate() {

    var isValid = true;

    var quantity = document.getElementById("quantity").value;
    var maxQuantity = document.getElementById("quantity").getAttribute("data-max");
    var local = document.getElementById("local").getAttribute("data-item");

    var errorMinMessage_ru = "*Значение д.б. больше 1";
    var errorMinMessage_en = "*Value must be greater than 1";
    var errorMaxMessage_ru = "*Значение д.б. меньше ";
    var errorMaxMessage_en = "*value must be less than ";

    if (quantity <= 0) {
        if (local == "ru") {
            document.getElementById("orderQuantityErr").innerHTML = errorMinMessage_ru;
        } else {
            document.getElementById("orderQuantityErr").innerHTML = errorMinMessage_en;
        }

        isValid = false;
    }

    if (Number(quantity) > Number(maxQuantity)) {
        if (local == "ru") {
            document.getElementById("orderQuantityErr").innerHTML = errorMaxMessage_ru.concat(maxQuantity);
        } else {
            document.getElementById("orderQuantityErr").innerHTML = errorMaxMessage_en.concat(maxQuantity);
        }

        isValid = false;
    }

    return isValid;
}