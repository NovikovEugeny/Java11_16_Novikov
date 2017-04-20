<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <script src="../../../js/drugFormValidator.js"></script>
    <div id="local" data-item="${sessionScope.local}"></div>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="pharmacist.drugForm" var="title"/>
    <fmt:message bundle="${loc}" key="order.list" var="orderList"/>
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
    <fmt:message bundle="${loc}" key="drugs.list" var="drugList"/>
    <fmt:message bundle="${loc}" key="drug.add" var="addDrug"/>
    <fmt:message bundle="${loc}" key="sales.report" var="salesReport"/>
    <fmt:message bundle="${loc}" key="fill.form" var="fillForm"/>
    <fmt:message bundle="${loc}" key="drugName" var="drugName"/>
    <fmt:message bundle="${loc}" key="pharm.group" var="group"/>
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
    <fmt:message bundle="${loc}" key="pills" var="pills"/>
    <fmt:message bundle="${loc}" key="capsules" var="capsules"/>
    <fmt:message bundle="${loc}" key="powder" var="powder"/>
    <fmt:message bundle="${loc}" key="pills" var="pills"/>
    <fmt:message bundle="${loc}" key="drops" var="drops"/>
    <fmt:message bundle="${loc}" key="solution" var="solution"/>
    <fmt:message bundle="${loc}" key="mixture" var="mixture"/>
    <fmt:message bundle="${loc}" key="suspension" var="suspensia"/>
    <fmt:message bundle="${loc}" key="aerosol" var="aerosol"/>
    <fmt:message bundle="${loc}" key="drugAmount" var="drugAmount"/>
    <fmt:message bundle="${loc}" key="drugForm" var="form"/>
    <fmt:message bundle="${loc}" key="drugAS" var="AS"/>
    <fmt:message bundle="${loc}" key="country" var="country"/>
    <fmt:message bundle="${loc}" key="dispensing" var="dispensing"/>
    <fmt:message bundle="${loc}" key="price" var="price"/>
    <fmt:message bundle="${loc}" key="quantity" var="quantity"/>
    <fmt:message bundle="${loc}" key="add" var="add"/>
    <fmt:message bundle="${loc}" key="on" var="on"/>
    <fmt:message bundle="${loc}" key="without" var="without"/>
    <fmt:message bundle="${loc}" key="drugname.error.message" var="drugNameErr"/>
    <fmt:message bundle="${loc}" key="drugamount.error.message" var="drugAmountErr"/>
    <fmt:message bundle="${loc}" key="as.error.message" var="asErr"/>
    <fmt:message bundle="${loc}" key="prodcountry.error.message" var="countryErr"/>
    <fmt:message bundle="${loc}" key="price.error.message" var="priceErr"/>
    <fmt:message bundle="${loc}" key="quantity.error.message" var="quantityErr"/>
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
            <section class="drug-form">
                <h3>${fillForm}</h3>
                <p><br></p>
                <form action="controller" method="post" onsubmit="return validae()">
                    <input type="hidden" name="command" value="add_new_drug"/>
                    <div class="form-group">
                        <label for="name">${drugName}:</label>
                        <p id="nameErr">
                            <c:if test="${not empty requestScope.errorMap['invalidDrugName']}">
                                <c:out value="${drugNameErr}"/>
                            </c:if>
                        </p>
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                    <div class="form-group">
                        <label for="group">${group}:</label>
                        <select class="form-control" id="group" name="group">
                            <option value="antibiotics">${antibiotics}</option>
                            <option value="antipyretics">${antipyretics}</option>
                            <option value="antiviral">${antiviral}</option>
                            <option value="allergy">${allergy}</option>
                            <option value="antiseptics">${antiseptics}</option>
                            <option value="cardiovascular">${cardio}</option>
                            <option value="immunity">${immunity}</option>
                            <option value="gastrointestinal">${gastr}</option>
                            <option value="otolaryngology">${lor}</option>
                            <option value="nervous system">${nerv}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="form">${form}:</label>
                        <select class="form-control" id="form" name="form">
                            <option value="pills">${pills}</option>
                            <option value="capsules">${capsules}</option>
                            <option value="powder">${powder}</option>
                            <option value="drops">${drops}</option>
                            <option value="solution">${solution}</option>
                            <option value="mixture">${mixture}</option>
                            <option value="suspension">${suspensia}</option>
                            <option value="aerosol">${aerosol}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="amount">${drugAmount}:</label>
                        <p id="amountErr">
                            <c:if test="${not empty requestScope.errorMap['invalidDrugAmount']}">
                                <c:out value="${drugAmountErr}"/>
                            </c:if>
                        </p>
                        <input type="text" class="form-control" id="amount" name="drugAmount">
                    </div>
                    <div class="form-group">
                        <label for="as">${AS}:</label>
                        <p id="activeSubstancesErr">
                            <c:if test="${not empty requestScope.errorMap['invalidAS']}">
                                <c:out value="${asErr}"/>
                            </c:if>
                        </p>
                        <textarea class="form-control" rows="5" id="as" name="activeSubstances"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="country">${country}:</label>
                        <p id="countryErr">
                            <c:if test="${not empty requestScope.errorMap['invalidCountry']}">
                                <c:out value="${countryErr}"/>
                            </c:if>
                        </p>
                        <input type="text" class="form-control" id="country" name="country">
                    </div>
                    <div class="form-group">
                        <label for="dispensing">${dispensing}:</label>
                        <select class="form-control" id="dispensing" name="dispensing">
                            <option value="without prescription">${without}</option>
                            <option value="on prescription">${on}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="price">${price}:</label>
                        <p id="priceErr">
                            <c:if test="${not empty requestScope.isValid}">
                                <c:out value="${quantityErr}"/>
                            </c:if>
                            <c:if test="${not empty requestScope.errorMap['invalidPrice']}">
                                <c:out value="${priceErr}"/>
                            </c:if>
                        </p>
                        <input type="text" class="form-control" id="price" name="price">
                    </div>
                    <div class="form-group">
                        <label for="quantity">${quantity}:</label>
                        <p id="quantityErr">
                            <c:if test="${not empty requestScope.isValid}">
                                <c:out value="${quantityErr}"/>
                            </c:if>
                        </p>
                        <input type="number" class="form-control" id="quantity" name="quantity" min="1">
                    </div>
                    <button type="submit" class="btn-success btn-lg">${add}</button>
                </form>
            </section>
        </div>
    </div>
</div>
</body>
</html>