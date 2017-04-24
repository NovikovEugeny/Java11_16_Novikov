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
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <script src="../../../js/recipeCodeValidator.js"></script>
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
            <section class="recipe-application">
                <h3>Заявка на продление рецепта</h3>
                <form action="controller" method="post" onsubmit="return validat()">
                    <div class="form-group">
                        <input type="hidden" name="command" value="send_recipe_extension_request">
                        <label for="code">recipe code:</label>
                        <p id="codeErr">
                            <c:if test="${not empty requestScope.isValid}">
                                fobidden empty
                            </c:if>
                        </p>
                        <input type="text" class="form-control" id="code" name="recipeCode">
                    </div>
                    <button type="submit" class="btn-success btn-lg">send</button>
                </form>
            </section>
        </div>
    </div>
</div>
<c:set var="response" value="${requestScope.execution}"/>
<c:if test="${not empty requestScope.execution}">
    <script>
        alert("${requestScope.execution}");
    </script>
</c:if>
<c:if test="${not empty requestScope.isExists}">
    <script>
        alert("is not exists");
    </script>
</c:if>
</body>
</html>