<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/main.css" rel="stylesheet">
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
                <h1>${user.surname} ${user.name} ${user.patronymic}</h1>
                <hr>
            </div>
        </div>
    </div>
</header>
<section class="recipe-list">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h3>Список заявок на рецепт</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>request date</th>
                            <th>code</th>
                            <th>name</th>
                            <th>group</th>
                            <th>form</th>
                            <th>amount</th>
                            <th>active substances</th>
                            <th>country</th>
                            <th>quantity</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="element" items="${recipeList}" >
                            <tr bgcolor="#FFFFFF">
                                <td><c:out value="${element.key.requestDate}"/></td>
                                <td><c:out value="${element.key.recipeCode}"/></td>
                                <td><c:out value="${element.value.name}"/></td>
                                <td><c:out value="${element.value.group}"/></td>
                                <td><c:out value="${element.value.form}"/></td>
                                <td><c:out value="${element.value.drugAmount}"/></td>
                                <td><c:out value="${element.value.activeSubstances}"/></td>
                                <td><c:out value="${element.value.country}"/></td>
                                <td><c:out value="${element.value.quantity}"/></td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="approve">
                                        <input type="hidden" name="recipeId" value="${element.key.id}">
                                        <button type="submit">approve</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="deny">
                                        <input type="hidden" name="recipeId" value="${element.key.id}">
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
</body>
</html>
