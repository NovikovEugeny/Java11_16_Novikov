<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>extend recipe</title>
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