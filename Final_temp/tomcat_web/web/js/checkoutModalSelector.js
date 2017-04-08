$("#modalWithout").on("show.bs.modal", function (event) {
    // получить кнопку, которая его открыло
    var button = $(event.relatedTarget);

    var id = button.data("id");
    var price = button.data("price");
    var group = button.data("group");

    var quantity = button.data("quantity");

    $(this).find("#withoutId").val(id);
    $(this).find("#withoutPrice").val(price);
    $(this).find("#withoutGroup").val(group);

    document.querySelector("#withoutQuantity").setAttribute("max", quantity);
})

$("#modalWith").on("show.bs.modal", function (event) {
    // получить кнопку, которая его открыло
    var button = $(event.relatedTarget);

    var id = button.data("id");
    var price = button.data("price");
    var group = button.data("group");

    var quantity = button.data("quantity");

    $(this).find("#withId").val(id);
    $(this).find("#withPrice").val(price);
    $(this).find("#withGroup").val(group);

    document.querySelector("#withQuantity").setAttribute("max", quantity);
})