function validate() {
    var isValid = true;

    var quantity = document.getElementById("n").value;
    var errorMessage1 = "*required";
    var errorMessage2 = "*not less 1";

    if (quantity == "") {
        document.getElementById("addDrugQuantityErr").innerHTML = errorMessage1;
        isValid = false;
    }
    if (quantity <= 0) {
        document.getElementById("addDrugQuantityErr").innerHTML = errorMessage2;
        isValid = false;
    }

    return isValid;
}