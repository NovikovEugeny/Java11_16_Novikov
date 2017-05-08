<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <script src="../../../js/jquery-3.2.0.js"></script>
    <script src="../../../js/bootstrap.js"></script>
    <script src="../../../js/jquery.maskedinput.js"></script>
    <script src="../../../js/validator/replenishValidator.js"></script>
    <div id="local" data-item="${sessionScope.local}"></div>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
    <fmt:message bundle="${loc}" key="balance.title" var="title"/>
    <fmt:message bundle="${loc}" key="order.drug" var="orderDrug"/>
    <fmt:message bundle="${loc}" key="order.erecipe" var="orderER"/>
    <fmt:message bundle="${loc}" key="cancel.order" var="cancelOrder"/>
    <fmt:message bundle="${loc}" key="extend.recipe" var="extendRecipe"/>
    <fmt:message bundle="${loc}" key="messages" var="messages"/>
    <fmt:message bundle="${loc}" key="purchase.history" var="history"/>
    <fmt:message bundle="${loc}" key="balance" var="balance"/>
    <fmt:message bundle="${loc}" key="balance.info" var="balanceInfo"/>
    <fmt:message bundle="${loc}" key="replenishment.balance" var="replenishTitle"/>
    <fmt:message bundle="${loc}" key="replenish" var="replenish"/>
    <fmt:message bundle="${loc}" key="card" var="card"/>
    <fmt:message bundle="${loc}" key="amount.money" var="money"/>
    <fmt:message bundle="${loc}" key="noscript" var="noscript"/>
    <fmt:message bundle="${loc}" key="error" var="error"/>
    <fmt:message bundle="${loc}" key="error.amount.money.message" var="errorAmount"/>
    <fmt:message bundle="${loc}" key="close" var="close"/>
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
            <noscript>
                <p align="center">${noscript}</p>
            </noscript>
            <section class="client-balance">
                <h3>${title}:</h3>
                <p align="center">${balanceInfo}:
                    <ctg:formatCost value="${requestScope.balance}" locale="${sessionScope.local}"/>
                </p>
                <button type="button" data-toggle="modal"
                        data-target="#modal">${replenish}
                </button>
            </section>
        </div>
    </div>
</div>
<!-- Modal-->
<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel1">${replenishTitle}</h4>
            </div>
            <form action="controller" method="post" onsubmit="return validate()">
                <input type="hidden" name="command" value="replenish_balance">
                <div class="modal-body">
                    <div class="balance">
                        <div class="form-group">
                            <label for="card">${card}:</label>
                            <p id="cardErr"></p>
                            <input type="text" class="form-control" id="card">
                        </div>
                        <div class="form-group">
                            <label for="money">${money}:</label>
                            <p id="moneyErr"></p>
                            <input type="text" name="moneyAmount" class="form-control" id="money">
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">${replenish}</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal2-->
<div class="modal fade" id="modalError" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel2">${error}</h4>
            </div>
            <p align="center">${errorAmount}</p>
            <div class="modal-footer">
                <button type="submit" class="btn-success btn-lg" data-dismiss="modal">${close}</button>
            </div>
        </div>
    </div>
</div>
<c:if test="${not empty requestScope.isValid}">
    <script>
        $(document).ready(function () {
            $("#modalError").modal('show');
        });
    </script>
</c:if>
<script>
    $(function () {
        $("#card").mask("9999 9999 9999 9999", {placeholder: "*"});
    });
</script>
</body>
</html>