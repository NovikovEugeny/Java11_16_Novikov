<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <script src="../../../js/jquery-3.2.0.js"></script>
    <script src="../../../js/bootstrap.js"></script>
    <title>Title</title>
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
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
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
        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
            <section class="recipe-description">
                <c:set var="recipe" value="${requestScope.RD}"/>
                <c:choose>
                    <c:when test="${not empty pageScope.recipe and pageScope.recipe.status eq 'open'}">
                        <h3>Описание рецепта</h3>
                    </c:when>
                    <c:when test="${pageScope.recipe.status eq 'closed'}">
                        <h3>Данный рецепт закрыт</h3>
                    </c:when>
                    <c:when test="${empty pageScope.recipe}">
                        <h3>Такой рецепт не существует, проверьте правильность ввода</h3>
                    </c:when>
                </c:choose>
                <c:if test="${not empty pageScope.recipe}">
                    <ul class="list-group">
                        <li class="list-group-item">
                            Препарат: ${pageScope.recipe.drugName}
                        </li>
                        <li class="list-group-item">
                            Фармакологическая группа: ${recipe.drugGroup}
                        </li>
                        <li class="list-group-item">
                            Форма: ${recipe.drugForm}
                        </li>
                        <li class="list-group-item">
                            кол-во препарата: ${recipe.activeSubstances}
                        </li>
                        <li class="list-group-item">
                            Страна производитель: ${recipe.country}
                        </li>
                        <li class="list-group-item">
                            Стоимость: ${recipe.price}
                        </li>
                        <li class="list-group-item">
                            кол-во шт: ${recipe.quantity}
                        </li>
                        <li class="list-group-item">
                            Статус рецепта: ${recipe.status}
                        </li>
                        <li class="list-group-item">
                            Действителен до: ${recipe.endDate}
                        </li>
                        <li class="list-group-item">
                            Сумма к оплате: ${recipe.cost}
                        </li>
                    </ul>
                </c:if>
                <c:if test="${recipe.status eq 'open'}">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="order_with_recipe">
                        <input type="hidden" name="recipeCode" value="${recipe.recipeCode}">
                        <input type="hidden" name="drugId" value="${recipe.drugId}">
                        <input type="hidden" name="quantity" value="${recipe.quantity}">
                        <input type="hidden" name="cost" value="${recipe.cost}">
                        <button type="submit" class="btn-success btn-lg">заказать</button>
                    </form>
                </c:if>
            </section>
        </div>
    </div>
</div>
<c:choose>
    <c:when test="${requestScope.execution eq 'quantity'}">
        <c:set var="errorMessage" value="quantity"/>
        <script>
            $(document).ready(function () {
                $("#modal").modal('show');
            });
        </script>
    </c:when>
    <c:when test="${requestScope.execution eq 'time'}">
        <c:set var="errorMessage" value="time"/>
        <script>
            $(document).ready(function () {
                $("#modal").modal('show');
            });
        </script>
    </c:when>
    <c:when test="${requestScope.execution eq 'money'}">
        <c:set var="errorMessage" value="money"/>
        <script>
            $(document).ready(function () {
                $("#modal").modal('show');
            });
        </script>
    </c:when>
</c:choose>
<!-- Modal-->
<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel1">error</h4>
            </div>
            <p align="center">${errorMessage}</p>
            <div class="modal-footer">
                <button type="submit" class="btn-success btn-lg" data-dismiss="modal">close</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>