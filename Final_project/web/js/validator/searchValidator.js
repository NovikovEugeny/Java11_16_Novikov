function validate() {

    var isValid = true;

    var local = document.getElementById("local").getAttribute("data-item");
    var drugName = document.getElementById("drugName").value;

    var pattern_en = /^[A-Za-z][a-z]{2,}$/;
    var pattern_ru = /^[А-Яа-я][а-я]{2,}$/;

    var errorMessage_ru = "*Неверное название препарата, не менее 3-х букв";
    var errorMessage_en = "*Incorrect drug name, at least 3 letters";

    if (!(pattern_en.test(drugName) || pattern_ru.test(drugName))) {
        if (local == "ru") {
            document.getElementById("search-error").innerHTML = errorMessage_ru;
        } else {
            document.getElementById("search-error").innerHTML = errorMessage_en;
        }
        isValid = false;
    }

    return isValid;
}