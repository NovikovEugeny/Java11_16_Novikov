<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/main.css" rel="stylesheet">
    <script src="../../js/jquery-3.2.0.js"></script>
    <script src="../../js/bootstrap.js"></script>
    <title>drug list to order</title>
</head>
<body>
<header>
    <div class="header-topline">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="logOut">
                        <a href="controller?command=log_out">выйти</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="greeting">
        <div class="row">
            <div class="col-md-12">
                <h1>
                    ${sessionScope.user.surname}
                    ${sessionScope.user.name}
                    ${sessionScope.user.patronymic}
                </h1>
                <hr>
            </div>
        </div>
    </div>
</header>
<div class="center">
    <div class="row">
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-3">
            <nav class="service-list">
                <form action="clientPharmGroups">
                    <button type="submit">заказать препарат</button>
                </form>
                <form action="clientOrderByERecipe">
                    <button type="submit">заказать по эл. рецепту</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="client_show_order_list">
                    <button type="submit">отменить заказ</button>
                </form>
                <form action="clientExtendRecipe">
                    <button>продлить рецепт</button>
                </form>
                <form>
                    <button type="submit">сообщения</button>
                </form>
                <form>
                    <button type="submit">история покупок</button>
                </form>
                <form>
                    <button type="submit">баланс на карте</button>
                </form>
            </nav>
        </div>
        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-9">
            <section class="client-druglist">
                <h3>Список препаратов</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>name</th>
                            <th>group</th>
                            <th>form</th>
                            <th>drug amount</th>
                            <th>active substances</th>
                            <th>country</th>
                            <th>dispensing</th>
                            <th>price</th>
                            <th>quantity</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="element" items="${requestScope.drugs}">
                            <tr>
                                <td><c:out value="${element.name}"/></td>
                                <td><c:out value="${element.group}"/></td>
                                <td><c:out value="${element.form}"/></td>
                                <td><c:out value="${element.drugAmount}"/></td>
                                <td><c:out value="${element.activeSubstances}"/></td>
                                <td><c:out value="${element.country}"/></td>
                                <td><c:out value="${element.dispensing}"/></td>
                                <td><c:out value="${element.price}"/></td>
                                <td><c:out value="${element.quantity}"/></td>
                                <td>
                                    <button type="button" class="btn btn-success" data-toggle="modal"
                                            data-target="#modal" data-id="${element.id}"
                                            data-price="${element.price}"
                                            data-quantity="${element.quantity}"
                                            data-group="${element.group}">checkout
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </section>
        </div>
    </div>
</div>
<!-- Modal-->
<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel1">Форма заказа</h4>
            </div>
            <form action="controller" method="post">
                <div class="modal-body">
                    <input type="hidden" name="command" value="order_without_recipe">
                    <input type="hidden" id="id" name="drugId">
                    <input type="hidden" id="price" name="price">
                    <input type="hidden" id="group" name="group">
                    <div class="form-group">
                        <label for="quantity">quantity:</label>
                        <input type="number" class="form-control"
                               id="quantity" name="quantity" min="1" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">to order</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $("#modal").on("show.bs.modal", function (event) {
        // получить кнопку, которая его открыло
        var button = $(event.relatedTarget);

        var id = button.data("id");
        var price = button.data("price");
        var group = button.data("group");
        var quantity = button.data("quantity");

        $(this).find("#id").val(id);
        $(this).find("#price").val(price);
        $(this).find("#group").val(group);

        document.querySelector("#quantity").setAttribute("max", quantity);
    })
</script>
<c:set var="response" value="${requestScope.executeMessage}"/>
<c:if test="${not empty response}">
    <script>
        alert("${response}");
    </script>
</c:if>
</body>
</html>
