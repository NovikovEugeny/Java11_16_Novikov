<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <style>
        #codeErr {
            color: red;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <script src="../../../js/validator/recipeCodeValidator.js"></script>
    <script src="../../../js/jquery-3.2.0.js"></script>
    <script src="../../../js/bootstrap.js"></script>
    <div id="local" data-item="${sessionScope.local}"></div>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
    <fmt:message bundle="${loc}" key="extend.recipe.title" var="title"/>
    <fmt:message bundle="${loc}" key="order.drug" var="orderDrug"/>
    <fmt:message bundle="${loc}" key="order.erecipe" var="orderER"/>
    <fmt:message bundle="${loc}" key="cancel.order" var="cancelOrder"/>
    <fmt:message bundle="${loc}" key="extend.recipe" var="extendRecipe"/>
    <fmt:message bundle="${loc}" key="messages" var="messages"/>
    <fmt:message bundle="${loc}" key="purchase.history" var="history"/>
    <fmt:message bundle="${loc}" key="balance" var="balance"/>
    <fmt:message bundle="${loc}" key="balance.info" var="balanceInfo"/>
    <fmt:message bundle="${loc}" key="quantity.error.message" var="quantityError"/>
    <fmt:message bundle="${loc}" key="extend.recipe.application" var="extendTitle"/>
    <fmt:message bundle="${loc}" key="recipe.code" var="recipeCode"/>
    <fmt:message bundle="${loc}" key="send" var="send"/>
    <fmt:message bundle="${loc}" key="not.expired" var="notExpired"/>
    <fmt:message bundle="${loc}" key="not.exists.recipe" var="notExists"/>
    <fmt:message bundle="${loc}" key="close" var="close"/>
    <fmt:message bundle="${loc}" key="error" var="error"/>
    <fmt:message bundle="${loc}" key="duplicate" var="duplicate"/>
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
            <section class="recipe-application">
                <h3>${extendTitle}</h3>
                <div class="form-group">
                    <label for="code">${recipeCode}:</label>
                    <p id="codeErr">
                        <c:if test="${not empty requestScope.isValid}">
                            <c:out value="${quantityError}"/>
                        </c:if>
                    </p>
                    <input type="text" class="form-control" id="code" name="recipeCode">
                </div>
                <button class="btn-success btn-lg" onclick="sendRequest()">${send}</button>
            </section>
            <c:out value="${requestScope.test}"/>
        </div>
    </div>
</div>
<script>
    function sendRequest() {
        if (validate()) {
            $.ajax({
                async : true,
                cache : false,
                url: '/controller',
                method: "post",
                data: {'command': 'send_recipe_extension_request', 'recipeCode': $("#code").val()},
                error: function (message) {
                    console.log(message);
                },
                success: function (data) {
                    $("#message").text(data);
                    $(document).ready(function () {
                        $("#modal").modal('show');
                    });
                }
            });
        }
    }
</script>
<!--
<c:if test="${not empty requestScope.execution}">
    <c:set var="response" value="${notExpired}"/>
    <script>
        $(document).ready(function () {
            $("#modal").modal('show');
        });
    </script>
</c:if>
<c:if test="${not empty requestScope.isExists}">
    <c:set var="response" value="${notExists}"/>
    <script>
        $(document).ready(function () {
            $("#modal").modal('show');
        });
    </script>
</c:if>
<c:if test="${not empty requestScope.isDuplicate}">
    <c:set var="response" value="${duplicate}"/>
    <script>
        $(document).ready(function () {
            $("#modal").modal('show');
        });
    </script>
</c:if>
-->
<!-- Modal-->
<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel1">${error}</h4>
            </div>
            <p align="center" id="message"></p>
            <div class="modal-footer">
                <button type="submit" class="btn-success btn-lg" data-dismiss="modal">${close}</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>