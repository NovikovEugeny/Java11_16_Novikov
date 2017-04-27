<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="messages" var="title"/>
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
    <fmt:message bundle="${loc}" key="order.drug" var="orderDrug"/>
    <fmt:message bundle="${loc}" key="order.erecipe" var="orderER"/>
    <fmt:message bundle="${loc}" key="cancel.order" var="cancelOrder"/>
    <fmt:message bundle="${loc}" key="extend.recipe" var="extendRecipe"/>
    <fmt:message bundle="${loc}" key="messages" var="messages"/>
    <fmt:message bundle="${loc}" key="purchase.history" var="history"/>
    <fmt:message bundle="${loc}" key="balance" var="balance"/>
    <fmt:message bundle="${loc}" key="quant" var="quant"/>
    <fmt:message bundle="${loc}" key="sent.addres" var="sent"/>
    <fmt:message bundle="${loc}" key="received" var="received"/>
    <fmt:message bundle="${loc}" key="recipe.with.code" var="recipeWithCode"/>
    <fmt:message bundle="${loc}" key="hide" var="hide"/>
    <fmt:message bundle="${loc}" key="closed" var="closed"/>
    <fmt:message bundle="${loc}" key="open" var="open"/>
    <fmt:message bundle="${loc}" key="extended" var="isExtended"/>
    <fmt:message bundle="${loc}" key="refuse" var="refuse"/>
    <fmt:message bundle="${loc}" key="medical.advice" var="medicalAdvice"/>
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
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-3">
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
        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-9">
            <section class="client-messages">
                <h3>${messages}:</h3>
                <ul class="list-group">
                    <c:forEach var="element" items="${requestScope.sendingMessages}">
                        <li class="list-group-item">
                            <fmt:formatDate value="${element.responseDate}" type="both" dateStyle="medium" timeStyle="medium"/>
                            ${element.drugName}  ${element.drugAmount} ${element.quantity} ${quant}
                            ${element.productingCountry} ${sent}
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="report_about_delivery">
                                <input type="hidden" name="orderId" value="${element.orderId}">
                                <button type="submit" class="btn-success">${received}</button>
                            </form>
                        </li>
                    </c:forEach>

                    <c:set var="status" value="${}"/>

                    <c:forEach var="element" items="${requestScope.doctorResponseMessages}">
                        <li class="list-group-item">
                            ${recipeWithCode} ${element.recipeCode}
                                <c:if test="${element.status eq 'denied'}">
                                    <c:out value="${refuse}"/>
                                </c:if>
                                <c:if test="${element.status eq 'approved'}">
                                    <c:out value="${isExtended}"/>
                                </c:if>
                                <fmt:formatDate value="${element.responseDate}" type="both" dateStyle="medium" timeStyle="medium"/>
                                <c:if test="${element.status eq 'denied'}">
                                    <c:out value="${medicalAdvice}"/>
                                </c:if>
                            <form>
                                <input type="hidden" name="command" value="hide_message">
                                <input type="hidden" name="requestId" value="${element.id}">
                                <button type="submit" class="btn-success">${hide}</button>
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