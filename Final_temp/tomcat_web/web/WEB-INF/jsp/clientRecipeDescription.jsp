<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/main.css" rel="stylesheet">
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
            <section class="recipe-description">
                <c:set var="recipe" value="${requestScope.RD}"/>
                <c:choose>
                    <c:when test="${not empty recipe and recipe.status eq 'open'}">
                        <h3>Описание рецепта</h3>
                        <ul class="list-group">
                            <li class="list-group-item">
                                Препарат: ${recipe.drugName}
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
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="order_with_recipe">
                            <input type="hidden" name="recipeCode" value="${recipe.recipeCode}">
                            <input type="hidden" name="drugId" value="${recipe.drugId}">
                            <input type="hidden" name="quantity" value="${recipe.quantity}">
                            <input type="hidden" name="cost" value="${recipe.cost}">
                            <button type="submit">заказать</button>
                        </form>
                    </c:when>
                    <c:when test="${recipe.status eq 'closed'}">
                        <p align="center">Данный рецепт закрыт</p>
                    </c:when>
                    <c:when test="${empty recipe}">
                        <p align="center">Такой рецепт не существует, проверьте правильность ввода</p>
                    </c:when>
                </c:choose>
            </section>
        </div>
    </div>
</div>
</body>
</html>
