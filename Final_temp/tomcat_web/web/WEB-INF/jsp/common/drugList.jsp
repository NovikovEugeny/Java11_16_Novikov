<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <title>drugs</title>
</head>
<body>
<header>
    <div class="header-topline">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="goto-main">
                        <a href="controller?command=start_page">на главную</a>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="auth">
                        <a href="controller?command=sign_in_page">Вход</a> /
                        <a href="controller?command=sign_up_page">Регистрация</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="logo">
        <div class="row">
            <div class="col-md-12">
                <img src="../../../images/logo.jpg">
                <h1>Онлайн-аптека</h1>
                <hr>
            </div>
        </div>
    </div>
</header>
<c:if test="${not empty requestScope.drugs}">
<section class="drug-list">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="drug-list">
                    <h3>Перечень доступных препаратов:</h3>

                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>имя</th>
                                <th>группа</th>
                                <th>форма</th>
                                <th>количество препарата</th>
                                <th>активные вещества</th>
                                <th>страна</th>
                                <th>отпуск</th>
                                <th>стоимость</th>
                                <th>количество</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="element" items="${requestScope.drugs}">
                                <tr bgcolor="#FFFFFF">
                                    <td><c:out value="${element.name}"/></td>
                                    <td><c:out value="${element.group}"/></td>
                                    <td><c:out value="${element.form}"/></td>
                                    <td><c:out value="${element.drugAmount}"/></td>
                                    <td><c:out value="${element.activeSubstances}"/></td>
                                    <td><c:out value="${element.country}"/></td>
                                    <td><c:out value="${element.dispensing}"/></td>
                                    <td><c:out value="${element.price}"/></td>
                                    <td><c:out value="${element.quantity}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</c:if>
<c:if test="${empty requestScope.drugs}">
    <p align="center">Ничего не найдено</p>
</c:if>
</body>
</html>
