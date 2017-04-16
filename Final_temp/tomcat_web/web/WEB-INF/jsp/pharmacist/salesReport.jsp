<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
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
        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
            <section class="pharmacist-orderlist">
                    <h3>Список заказов:</h3>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>date</th>
                                <th>client mobile</th>
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
                            <c:forEach var="element" items="${requestScope.}" >
                                <tr>
                                    <td><c:out value="${element.requestDate}"/></td>
                                    <td><c:out value="${element.clientMobile}"/></td>
                                    <td><c:out value="${element.drugName}"/></td>
                                    <td><c:out value="${element.pharmacologicalGroup}"/></td>
                                    <td><c:out value="${element.drugForm}"/></td>
                                    <td><c:out value="${element.drugAmount}"/></td>
                                    <td><c:out value="${element.activeSubstances}"/></td>
                                    <td><c:out value="${element.productingCountry}"/></td>
                                    <td><c:out value="${element.quantity}"/></td>
                                    <td>
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="send">
                                            <input type="hidden" name="orderId" value="${element.orderId}">
                                            <button type="submit">send</button>
                                        </form>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-success" data-toggle="modal"
                                                data-target="#cancelModal" data-id="${element.orderId}">cancel
                                        </button>
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
