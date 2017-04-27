<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <script src="../../../js/jquery-3.2.0.js"></script>
    <script src="../../../js/bootstrap.js"></script>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
    <fmt:message bundle="${loc}" key="recipe.description" var="title"/>
    <fmt:message bundle="${loc}" key="order.drug" var="orderDrug"/>
    <fmt:message bundle="${loc}" key="order.erecipe" var="orderER"/>
    <fmt:message bundle="${loc}" key="cancel.order" var="cancelOrder"/>
    <fmt:message bundle="${loc}" key="extend.recipe" var="extendRecipe"/>
    <fmt:message bundle="${loc}" key="messages" var="messages"/>
    <fmt:message bundle="${loc}" key="purchase.history" var="history"/>
    <fmt:message bundle="${loc}" key="balance" var="balance"/>
    <fmt:message bundle="${loc}" key="closed.recipe" var="closedTitle"/>
    <fmt:message bundle="${loc}" key="not.exists.incorrect.code" var="notExists"/>
    <fmt:message bundle="${loc}" key="drug.name" var="drugName"/>
    <fmt:message bundle="${loc}" key="pharm.group" var="pharmGroup"/>
    <fmt:message bundle="${loc}" key="drugForm" var="drugForm"/>
    <fmt:message bundle="${loc}" key="drugAmount" var="drugAmount"/>
    <fmt:message bundle="${loc}" key="country" var="country"/>
    <fmt:message bundle="${loc}" key="price" var="price"/>
    <fmt:message bundle="${loc}" key="quantity" var="quantity"/>
    <fmt:message bundle="${loc}" key="quant" var="quant"/>
    <fmt:message bundle="${loc}" key="recipe.status" var="recipeStatus"/>
    <fmt:message bundle="${loc}" key="valid.until" var="validUntil"/>
    <fmt:message bundle="${loc}" key="amount.to.be.paid" var="amountToBePaid"/>
    <fmt:message bundle="${loc}" key="order" var="order"/>
    <fmt:message bundle="${loc}" key="closed" var="closed"/>
    <fmt:message bundle="${loc}" key="open" var="open"/>
    <title>${title}</title>
</head>
<body>
<header>
    <div class="header-topline">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="logOut">
                        <a href="controller?command=log_out">${logout}</a>
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
                    <button type="submit">${orderDrug}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="order_by_erecipe_page">
                    <button type="submit">${orderER}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="client_show_order_list">
                    <button type="submit">${cancelOrder}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="extend_recipe_page">
                    <button>${extendRecipe}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_messages">
                    <button type="submit">${messages}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_shopping_list">
                    <button type="submit">${history}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_balance">
                    <button type="submit">${balance}</button>
                </form>
            </nav>
        </div>
        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
            <section class="recipe-description">
                <c:set var="recipe" value="${requestScope.RD}"/>
                <c:choose>
                    <c:when test="${not empty pageScope.recipe and pageScope.recipe.status eq 'open'}">
                        <h3>${title}:</h3>
                    </c:when>
                    <c:when test="${pageScope.recipe.status eq 'closed'}">
                        <h3>${closedTitle}</h3>
                    </c:when>
                    <c:when test="${empty pageScope.recipe}">
                        <h3>${notExists}</h3>
                    </c:when>
                </c:choose>
                <c:if test="${not empty pageScope.recipe}">
                    <ul class="list-group">
                        <li class="list-group-item">
                            ${drugName}: ${pageScope.recipe.drugName}
                        </li>
                        <li class="list-group-item">
                            ${pharmGroup}: ${recipe.drugGroup}
                        </li>
                        <li class="list-group-item">
                            ${drugForm}: ${recipe.drugForm}
                        </li>
                        <li class="list-group-item">
                            ${drugAmount}: ${recipe.activeSubstances}
                        </li>
                        <li class="list-group-item">
                            ${country}: ${recipe.country}
                        </li>
                        <li class="list-group-item">
                            ${price}: ${recipe.price}
                        </li>
                        <li class="list-group-item">
                            ${quantity} ${quant}: ${recipe.quantity}
                        </li>
                        <li class="list-group-item">
                            <c:if test="${recipe.status eq 'open'}">
                                ${recipeStatus}: ${open}
                            </c:if>
                            <c:if test="${recipe.status eq 'closed'}">
                                ${recipeStatus}: ${closed}
                            </c:if>
                        </li>
                        <li class="list-group-item">
                            ${validUntil}:<fmt:formatDate value="${recipe.endDate}" type="both" dateStyle="medium" timeStyle="medium"/>
                        </li>
                        <li class="list-group-item">
                            ${amountToBePaid}: ${recipe.cost}
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
                        <button type="submit" class="btn-success btn-lg">${order}</button>
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