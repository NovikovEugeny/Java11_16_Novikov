<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="order.list" var="orderList"/>
    <fmt:message bundle="${loc}" key="drugs.list" var="drugList"/>
    <fmt:message bundle="${loc}" key="drug.add" var="addDrug"/>
    <fmt:message bundle="${loc}" key="sales.report" var="salesReport"/>
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
    <fmt:message bundle="${loc}" key="groups" var="title"/>
    <fmt:message bundle="${loc}" key="select.group" var="selectGroup"/>
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
        <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
            <nav class="service-list">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="pharmacist_show_order_list">
                    <button type="submit">${orderList}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="groups_to_update_page">
                    <button type="submit">${drugList}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="add_drug_page">
                    <button type="submit">${addDrug}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_sales_report">
                    <button type="submit">${salesReport}</button>
                </form>
            </nav>
        </div>
        <div class="col-xs-12 col-sm-9 col-md-9 col-lg-9">
            <section class="pharm-groups">
                <h3>${selectGroup}</h3>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_update"/>
                    <input type="hidden" name="group" value="antibiotics"/>
                    <button type="submit">${antibiotics}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_update"/>
                    <input type="hidden" name="group" value="antiviral"/>
                    <button type="submit">${antiviral}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_update"/>
                    <input type="hidden" name="group" value="antipyretics"/>
                    <button type="submit">${antipyretics}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_update"/>
                    <input type="hidden" name="group" value="allergy"/>
                    <button type="submit">${allergy}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_update"/>
                    <input type="hidden" name="group" value="antiseptics"/>
                    <button type="submit">${antiseptics}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_update"/>
                    <input type="hidden" name="group" value="cardiovascular"/>
                    <button type="submit">${cardio}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_update"/>
                    <input type="hidden" name="group" value="immunity"/>
                    <button type="submit">${immunity}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_update"/>
                    <input type="hidden" name="group" value="gastrointestinal"/>
                    <button type="submit">${gastr}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_update"/>
                    <input type="hidden" name="group" value="otolaryngology"/>
                    <button type="submit">${lor}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_update"/>
                    <input type="hidden" name="group" value="nervous system"/>
                    <button type="submit">${nerv}</button>
                </form>
            </section>
        </div>
    </div>
</div>
</body>
</html>