function validate() {
    var isValid = true;

    var quantity = document.getElementById("n").value;
    var errorMessage = "*required";

    if (quantity == "") {
        document.getElementById("addDrugQuantityErr").innerHTML = errorMessage;
        isValid = false;
    }

    return isValid;
}