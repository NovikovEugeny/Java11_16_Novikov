<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/main.css" rel="stylesheet">
    <title>drugListToOrder</title>
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
<div class="center">
    <div class="row">
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-3">
            <nav class="service-list">
                <form action="clientPharmGroups">
                    <button type="submit">заказать препарат</button>
                </form>
                <form>
                    <button type="submit">отменить заказ</button>
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
            <section class="client-druglist">
                <h3>Список препаратов</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>name</th>
                            <th>group</th>
                            <th>form</th>
                            <th>drug amount</th>
                            <th>active substances</th>
                            <th>country</th>
                            <th>dispensing</th>
                            <th>price</th>
                            <th>quantity</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="element" items="${drugs}" >
                            <tr>
                                <td><c:out value="${element.name}" /></td>
                                <td><c:out value="${element.group}"/></td>
                                <td><c:out value="${element.form}"/></td>
                                <td><c:out value="${element.drugAmount}"/></td>
                                <td><c:out value="${element.activeSubstances}"/></td>
                                <td><c:out value="${element.country}"/></td>
                                <td><c:out value="${element.dispensing}"/></td>
                                <td><c:out value="${element.price}"/></td>
                                <td><c:out value="${element.quantity}"/></td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="checkout">
                                        <input type="hidden" name="drugId" value="${element.id}">
                                        <input type="hidden" name="price" value="${element.price}">
                                        <input type="hidden" name="dispensing" value="${element.dispensing}">
                                        <button type="submit">checkout</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </section>
        </div>
    </div>
</div>
</body>
</html>
