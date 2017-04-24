function validate() {

    var isValid = true;

    var quantity = document.getElementById("n").value;
    var local = document.getElementById("local").getAttribute("data-item");

    var errorMessage_ru = "*Значение д.б. больше 1";
    var errorMessage_en = "*Value must be greater than 1";

    if (quantity <= 0) {
        if (local == "ru") {
            document.getElementById("addDrugQuantityErr").innerHTML = errorMessage_ru;
        } else {
            document.getElementById("addDrugQuantityErr").innerHTML = errorMessage_en;
        }

        isValid = false;
    }

    return isValid;
}