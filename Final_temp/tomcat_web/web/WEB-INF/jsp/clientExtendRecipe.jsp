<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/main.css" rel="stylesheet">
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
                <form action="clientPharmGroups">
                    <button type="submit">заказать препарат</button>
                </form>
                <form action="clientOrderByERecipe">
                    <button type="submit">заказать по эл. рецепту</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="client_show_order_list">
                    <button type="submit">отменить заказ</button>
                </form>
                <form action="clientExtendRecipe">
                    <button>продлить рецепт</button>
                </form>
                <form>
                    <button type="submit">сообщения</button>
                </form>
                <form>
                    <button type="submit">история покупок</button>
                </form>
                <form>
                    <button type="submit">баланс на карте</button>
                </form>
            </nav>
        </div>
        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-9">
            <section class="recipe-application">
                <h3>Заявка на продление рецепта</h3>
                <form action="controller" method="post">
                    <div class="form-group">
                        <input type="hidden" name="command" value="send_recipe_extension_request">
                        <label for="code">recipe code:</label>
                        <input type="text" class="form-control" id="code" name="recipeCode" required>
                    </div>
                    <button type="submit" class="btn-success btn-lg">send</button>
                </form>
            </section>
        </div>
    </div>
</div>
</body>
</html>
