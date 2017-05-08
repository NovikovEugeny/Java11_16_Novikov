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
    <fmt:message bundle="${loc}" key="pharmacist.orders" var="title"/>
    <fmt:message bundle="${loc}" key="order.list" var="orderList"/>
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
    <fmt:message bundle="${loc}" key="no.orders" var="noOrders"/>
    <fmt:message bundle="${loc}" key="drugs.list" var="drugList"/>
    <fmt:message bundle="${loc}" key="drug.add" var="addDrug"/>
    <fmt:message bundle="${loc}" key="sales.report" var="salesReport"/>
    <fmt:message bundle="${loc}" key="date" var="date"/>
    <fmt:message bundle="${loc}" key="client.mobile" var="clientMobile"/>
    <fmt:message bundle="${loc}" key="drugName" var="drugName"/>
    <fmt:message bundle="${loc}" key="drugGroup" var="drugGroup"/>
    <fmt:message bundle="${loc}" key="drugForm" var="drugForm"/>
    <fmt:message bundle="${loc}" key="drugAmount" var="drugAmount"/>
    <fmt:message bundle="${loc}" key="drugAS" var="drugAS"/>
    <fmt:message bundle="${loc}" key="country" var="country"/>
    <fmt:message bundle="${loc}" key="quantity" var="quantity"/>
    <fmt:message bundle="${loc}" key="cancel" var="cancel"/>
    <fmt:message bundle="${loc}" key="send" var="send"/>
    <fmt:message bundle="${loc}" key="are.you.sure" var="ays"/>
    <fmt:message bundle="${loc}" key="cancelation" var="cacelMessage"/>
    <fmt:message bundle="${loc}" key="noscript" var="noscript"/>
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
            <section class="pharmacist-orderlist">
                <c:if test="${empty requestScope.orderList}">
                    <p align="center">${noOrders}</p>
                </c:if>
                <c:if test="${not empty requestScope.orderList}">
                    <noscript>
                         <p align="center">${noscript}</p>
                    </noscript>
                <h3>${orderList}:</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>${date}</th>
                            <th>${clientMobile}</th>
                            <th>${drugName}</th>
                            <th>${drugGroup}</th>
                            <th>${drugForm}</th>
                            <th>${drugAmount}</th>
                            <th>${drugAS}</th>
                            <th>${country}</th>
                            <th>${quantity}</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="element" items="${requestScope.orderList}" >
                            <tr>
                                <td><fmt:formatDate value="${element.requestDate}" type="both" dateStyle="medium" timeStyle="medium"/></td>
                                <td><c:out value="${element.clientMobile}"/></td>
                                <td><c:out value="${element.drugName}"/></td>
                                <td><c:out value="${element.pharmacologicalGroup}"/></td>
                                <td><c:out value="${element.drugForm}"/></td>
                                <td><c:out value="${element.drugAmount}"/></td>
                                <td><c:out value="${element.activeSubstances}"/></td>
                                <td><c:out value="${element.productingCountry}"/></td>
                                <td><c:out value="${element.quantity}"/></td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="send">
                                        <input type="hidden" name="orderId" value="${element.orderId}">
                                        <button type="submit" class="btn-success">${send}</button>
                                    </form>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success" data-toggle="modal"
                                            data-target="#cancelModal" data-id="${element.orderId}">${cancel}
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                </c:if>
            </section>
        </div>
    </div>
</div>
<!--Modal-->
<div class="modal fade" id="cancelModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel2">${cacelMessage}</h4>
            </div>
            <form action="controller" method="post">
                <div class="modal-body">
                    <input type="hidden" name="command" value="pharmacist_cancel_order">
                    <input type="hidden" id="orderId" name="orderId">
                    ${ays}?
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">${cancel}</button>
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