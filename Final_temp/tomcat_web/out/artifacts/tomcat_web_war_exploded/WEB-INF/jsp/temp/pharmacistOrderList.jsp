<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <title>order list</title>
</head>
<body>
<!--
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
</header>
<section class="recipe-list">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h3>Список заказов</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>request date</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="element" items="${orderList}" >
                            <tr bgcolor="#FFFFFF">
                                <td><c:out value="${ element.requestDate }"/></td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="show_recipe">
                                        <input type="hidden" name="recipeId" value="${ element.id }">
                                        <input type="submit" value="show">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
-->
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
                <h1>${user.surname} ${user.name} ${user.patronymic}</h1>
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
                    <input type="hidden" name="command" value="show_order_list">
                    <button type="submit">Список заказов</button>
                </form>
                <form>
                    <button type="submit">Добавить препарат</button>
                </form>
                <form>
                    <button type="submit">Удалить препарат</button>
                </form>
                <form>
                    <button type="submit">Отчет по продажам</button>
                </form>
            </nav>
        </div>

</body>
</html>
