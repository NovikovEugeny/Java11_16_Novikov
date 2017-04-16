<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <script src="../../../js/jquery-3.2.0.js"></script>
    <script src="../../../js/bootstrap.js"></script>
    <title>cancel order</title>
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
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="groups_to_order_page">
                    <button type="submit">заказать препарат</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="order_by_erecipe_page">
                    <button type="submit">заказать по эл. рецепту</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="client_show_order_list">
                    <button type="submit">отменить заказ</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="extend_recipe_page">
                    <button>продлить рецепт</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_messages">
                    <button type="submit">сообщения</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_shopping_list">
                    <button type="submit">история покупок</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_balance">
                    <button type="submit">баланс на карте</button>
                </form>
            </nav>
        </div>
        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-9">
            <section class="client-druglist">
                <h3>Заказы:</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>date</th>
                            <th>name</th>
                            <th>group</th>
                            <th>form</th>
                            <th>amount</th>
                            <th>active substances</th>
                            <th>country</th>
                            <th>quantity</th>
                            <th>cost</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="element" items="${requestScope.orderList}">
                            <tr>
                                <td><c:out value="${element.requestDate}"/></td>
                                <td><c:out value="${element.drugName}"/></td>
                                <td><c:out value="${element.pharmacologicalGroup}"/></td>
                                <td><c:out value="${element.drugForm}"/></td>
                                <td><c:out value="${element.drugAmount}"/></td>
                                <td><c:out value="${element.activeSubstances}"/></td>
                                <td><c:out value="${element.productingCountry}"/></td>
                                <td><c:out value="${element.quantity}"/></td>
                                <td><c:out value="${element.cost}"/></td>
                                <td>
                                    <button type="button" class="btn btn-success" data-toggle="modal"
                                            data-target="#cancelModal" data-id="${element.orderId}">cancel
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
<div class="modal fade" id="cancelModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel2">Отмена заказа</h4>
            </div>
            <form action="controller" method="post">
                <div class="modal-body">
                    <input type="hidden" name="command" value="client_cancel_order">
                    <input type="hidden" id="orderId" name="orderId">
                    Внимание, после подтверждения восстановление заказа невозможно!
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $("#cancelModal").on("show.bs.modal", function (event) {
        // получить кнопку, которая его открыло
        var button = $(event.relatedTarget);

        var orderId = button.data("id");

        $(this).find("#orderId").val(orderId);
    })
</script>
</body>
</html>