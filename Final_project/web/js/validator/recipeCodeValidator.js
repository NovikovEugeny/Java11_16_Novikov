function validate() {

    var isValid = true;

    var code = document.getElementById("code").value;
    var local = document.getElementById("local").getAttribute("data-item");

    var errorMessage_ru = "*обязательно для заполнения";
    var errorMessage_en = "*required";

    document.getElementById("codeErr").innerHTML = "";

    if (code == "") {
        if (local == "ru") {
            document.getElementById("codeErr").innerHTML = errorMessage_ru;
        } else {
            document.getElementById("codeErr").innerHTML = errorMessage_en;
        }

        isValid = false;
    }

    return isValid;
}