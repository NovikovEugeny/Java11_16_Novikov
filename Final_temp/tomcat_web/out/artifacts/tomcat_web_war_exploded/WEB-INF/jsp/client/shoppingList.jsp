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
                <h3>История покупок</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>date</th>
                            <th>name</th>
                            <th>group</th>
                            <th>amount</th>
                            <th>country</th>
                            <th>quantity</th>
                            <th>cost</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="element" items="${requestScope.shoppingList}">
                            <tr>
                                <td><c:out value="${element.requestDate}"/></td>
                                <td><c:out value="${element.drugName}"/></td>
                                <td><c:out value="${element.pharmacologicalGroup}"/></td>
                                <td><c:out value="${element.drugAmount}"/></td>
                                <td><c:out value="${element.productingCountry}"/></td>
                                <td><c:out value="${element.quantity}"/></td>
                                <td><c:out value="${element.cost}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </section>
        </div>
    </div>
</div>
</body>
</html>