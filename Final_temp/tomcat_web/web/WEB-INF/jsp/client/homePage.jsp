<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="button.cardio" var="cardio"/>
    <fmt:message bundle="${loc}" key="button.antiviral" var="a_v"/>
    <fmt:message bundle="${loc}" key="button.immunity" var="immunity"/>
    <fmt:message bundle="${loc}" key="button.antibiotics" var="antibio"/>
    <fmt:message bundle="${loc}" key="button.nerv" var="nerv"/>
    <fmt:message bundle="${loc}" key="button.allergy" var="allergy"/>
    <fmt:message bundle="${loc}" key="button.gastr" var="gastr"/>
    <fmt:message bundle="${loc}" key="button.antipyretics" var="antipyr"/>

    <title>Client page</title>
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
            <section class="client-messages">
                <h3>Сообщения</h3>
                <ul class="list-group">
                    <c:forEach var="element" items="${requestScope.sendingMessages}">
                        <li class="list-group-item">
                            ${element.responseDate}  ${element.drugName}  ${element.drugAmount}
                            ${element.quantity}шт.  ${element.productingCountry}  отправлен на ваш адрес
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="report_about_delivery">
                                <input type="hidden" name="orderId" value="${element.orderId}">
                                <button type="submit" class="btn-success">received</button>
                            </form>
                        </li>
                    </c:forEach>
                    <c:forEach var="element" items="${requestScope.doctorResponseMessages}">
                        <li class="list-group-item">
                            Рецепт с кодом ${element.recipeCode} ${element.status}
                            ${element.responseDate}
                            <form>
                                <input type="hidden" name="command" value="hide_message">
                                <input type="hidden" name="requestId" value="${element.id}">
                                <button type="submit" class="btn-success">hide</button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>


            </section>
        </div>
    </div>
</div>
</body>
</html>