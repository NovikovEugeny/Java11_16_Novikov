<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        ul {
            width:100%;
            margin-bottom:20px;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-flex-wrap: wrap;
            -ms-flex-wrap: wrap;
            flex-wrap: wrap;
        }
        li {
            float:left;
            display:inline;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
        }
        li { width:16.666%; }
    </style>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <script src="../../../js/jquery-3.2.0.js"></script>
    <script src="../../../js/bootstrap.js"></script>
    <title>Pharmacist page</title>
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
        <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
            <nav class="service-list">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="pharmacist_show_order_list">
                    <button type="submit">Список заказов</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="groups_to_update_page">
                    <button type="submit">Список репаратов</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="add_drug_page">
                    <button type="submit">Добавить препарат</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_sales_report">
                    <button type="submit">Отчет по продажам</button>
                </form>
            </nav>
        </div>
        <div class="col-xs-12 col-sm-9 col-md-9 col-lg-9">
            <section class="pharmacist-orderlist">
                    <h3>Отчет по продажам:</h3>
                <c:forEach var="element" items="${requestScope.salesReport}">
                    <p align="center"><fmt:formatDate pattern="dd-MM-yyyy" value="${element.key}"/></p>
                    <ul class="list-group">
                        <c:forEach var="inner" items="${element.value}">
                            <li class="list-group-item">
                                <c:out value="${inner.name}"/>
                            </li>
                            <li class="list-group-item">
                                <c:out value="${inner.group}"/>
                            </li>
                            <li class="list-group-item">
                                <c:out value="${inner.drugAmount}"/>
                            </li>
                            <li class="list-group-item">
                                <c:out value="${inner.activeSubstances}"/>
                            </li>
                            <li class="list-group-item">
                                <c:out value="${inner.country}"/>
                            </li>
                            <li class="list-group-item">
                                <c:out value="${inner.quantity}"/>
                            </li>
                        </c:forEach>
                    </ul>
                </c:forEach>
            </section>
        </div>
    </div>
</div>
</body>
</html>
