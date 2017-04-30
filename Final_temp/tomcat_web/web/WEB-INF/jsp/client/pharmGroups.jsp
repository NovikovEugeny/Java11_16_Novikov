<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <script src="../../../js/jquery-3.2.0.js"></script>
    <script src="../../../js/bootstrap.js"></script>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
    <fmt:message bundle="${loc}" key="pharm.group" var="title"/>
    <fmt:message bundle="${loc}" key="order.drug" var="orderDrug"/>
    <fmt:message bundle="${loc}" key="order.erecipe" var="orderER"/>
    <fmt:message bundle="${loc}" key="cancel.order" var="cancelOrder"/>
    <fmt:message bundle="${loc}" key="extend.recipe" var="extendRecipe"/>
    <fmt:message bundle="${loc}" key="messages" var="messages"/>
    <fmt:message bundle="${loc}" key="purchase.history" var="history"/>
    <fmt:message bundle="${loc}" key="balance" var="balance"/>
    <fmt:message bundle="${loc}" key="balance.info" var="balanceInfo"/>
    <fmt:message bundle="${loc}" key="drug.groups" var="groups"/>
    <fmt:message bundle="${loc}" key="button.cardio" var="cardio"/>
    <fmt:message bundle="${loc}" key="button.antiviral" var="antiviral"/>
    <fmt:message bundle="${loc}" key="button.immunity" var="immunity"/>
    <fmt:message bundle="${loc}" key="button.antibiotics" var="antibiotics"/>
    <fmt:message bundle="${loc}" key="button.nerv" var="nerv"/>
    <fmt:message bundle="${loc}" key="button.allergy" var="allergy"/>
    <fmt:message bundle="${loc}" key="button.gastr" var="gastr"/>
    <fmt:message bundle="${loc}" key="button.antipyretics" var="antipyretics"/>
    <fmt:message bundle="${loc}" key="button.antiseptics" var="antiseptics"/>
    <fmt:message bundle="${loc}" key="button.lor" var="lor"/>
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
            <section class="pharm-groups">
                <h3>${groups}</h3>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="antibiotics"/>
                    <button type="submit">${antibiotics}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="antiviral"/>
                    <button type="submit">${antiviral}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="antipyretics"/>
                    <button type="submit">${antipyretics}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="allergy"/>
                    <button type="submit">${allergy}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="antiseptics"/>
                    <button type="submit">${antiseptics}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="cardiovascular"/>
                    <button type="submit">${cardio}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="immunity"/>
                    <button type="submit">${immunity}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="gastrointestinal"/>
                    <button type="submit">${gastr}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="otolaryngology"/>
                    <button type="submit">${lor}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="nervous system"/>
                    <button type="submit">${nerv}</button>
                </form>
            </section>
        </div>
    </div>
</div>
</body>
</html>