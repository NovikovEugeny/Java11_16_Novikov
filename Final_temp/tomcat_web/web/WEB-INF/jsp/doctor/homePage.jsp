<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <title>doctor page</title>
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
<c:if test="${not empty requestScope.recipeList}">
<section class="recipe-list">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h3>Список заявок на продление рецепт</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>дата заявки</th>
                            <th>код рецепта</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="element" items="${requestScope.recipeList}">
                            <tr bgcolor="#FFFFFF">
                                <td><c:out value="${element.requestDate}"/></td>
                                <td><c:out value="${element.recipeCode}"/></td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="approve">
                                        <input type="hidden" name="id" value="${element.id}">
                                        <input type="hidden" name="recipeCode" value="${element.recipeCode}">
                                        <button type="submit">approve</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="deny">
                                        <input type="hidden" name="id" value="${element.id}">
                                        <input type="hidden" name="recipeCode" value="${element.recipeCode}">
                                        <button type="submit">deny</button>
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
</c:if>
<c:if test="${empty requestScope.recipeList}">
    <p align="center">Нет заявок</p>
</c:if>
</body>
</html>
