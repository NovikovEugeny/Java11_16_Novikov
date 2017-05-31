<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <style>
        #orderQuantityErr {
            color: red;
        }
    </style>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <script src="../../../js/jquery-3.2.0.js"></script>
    <script src="../../../js/bootstrap.js"></script>
    <script src="../../../js/validator/drugOrderQuantityValidator.js"></script>
    <div id="local" data-item="${sessionScope.local}"></div>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="drug.list" var="title"/>
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
    <fmt:message bundle="${loc}" key="order.drug" var="orderDrug"/>
    <fmt:message bundle="${loc}" key="order.erecipe" var="orderER"/>
    <fmt:message bundle="${loc}" key="cancel.order" var="cancelOrder"/>
    <fmt:message bundle="${loc}" key="extend.recipe" var="extendRecipe"/>
    <fmt:message bundle="${loc}" key="messages" var="messages"/>
    <fmt:message bundle="${loc}" key="purchase.history" var="history"/>
    <fmt:message bundle="${loc}" key="balance" var="balance"/>
    <fmt:message bundle="${loc}" key="drug.list" var="drugs"/>
    <fmt:message bundle="${loc}" key="date" var="date"/>
    <fmt:message bundle="${loc}" key="drugName" var="name"/>
    <fmt:message bundle="${loc}" key="drugGroup" var="group"/>
    <fmt:message bundle="${loc}" key="drugForm" var="form"/>
    <fmt:message bundle="${loc}" key="drugAmount" var="amount"/>
    <fmt:message bundle="${loc}" key="drugAS" var="AS"/>
    <fmt:message bundle="${loc}" key="country" var="country"/>
    <fmt:message bundle="${loc}" key="quantity" var="quantity"/>
    <fmt:message bundle="${loc}" key="cost" var="cost"/>
    <fmt:message bundle="${loc}" key="dispensing" var="dispensing"/>
    <fmt:message bundle="${loc}" key="price" var="price"/>
    <fmt:message bundle="${loc}" key="checkout" var="checkout"/>
    <fmt:message bundle="${loc}" key="order.form" var="orderForm"/>
    <fmt:message bundle="${loc}" key="order" var="order"/>
    <fmt:message bundle="${loc}" key="error" var="error"/>
    <fmt:message bundle="${loc}" key="not.enough.money" var="notMoney"/>
    <fmt:message bundle="${loc}" key="not.enough.drugs" var="notDrugs"/>
    <fmt:message bundle="${loc}" key="close" var="close"/>
    <fmt:message bundle="${loc}" key="invalid.quantity" var="invalid"/>
    <fmt:message bundle="${loc}" key="noscript" var="noscript"/>
    <fmt:message bundle="${loc}" key="on" var="on"/>
    <fmt:message bundle="${loc}" key="without" var="without"/>
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
            <noscript>
                <p align="center">${noscript}</p>
            </noscript>
            <section class="client-druglist">
                <h3>${drugs}:</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>${name}</th>
                            <th>${group}</th>
                            <th>${form}</th>
                            <th>${amount}</th>
                            <th>${AS}</th>
                            <th>${country}</th>
                            <th>${dispensing}</th>
                            <th>${price}</th>
                            <th>${quantity}</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="element" items="${requestScope.drugs}">
                            <tr>
                                <td><c:out value="${element.name}"/></td>
                                <td><c:out value="${element.group}"/></td>
                                <td><c:out value="${element.form}"/></td>
                                <td><c:out value="${element.drugAmount}"/></td>
                                <td><c:out value="${element.activeSubstances}"/></td>
                                <td><c:out value="${element.country}"/></td>
                                <td>
                                    <c:if test="${element.dispensing eq 'on prescription'}">
                                        <c:out value="${on}"/>
                                    </c:if>
                                    <c:if test="${element.dispensing eq 'without prescription'}">
                                        <c:out value="${without}"/>
                                    </c:if>
                                </td>
                                <td><ctg:formatCost value="${element.price}" locale="${sessionScope.local}"/></td>
                                <td><c:out value="${element.quantity}"/></td>
                                <td>
                                    <button type="button" class="btn btn-success" data-toggle="modal"
                                            data-target="#modal" data-id="${element.id}"
                                            data-price="${element.price}"
                                            data-quantity="${element.quantity}"
                                            data-group="${element.group}">${checkout}
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
<!-- Modal-->
<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel1">${orderForm}</h4>
            </div>
            <form action="controller" method="post" onsubmit="return validate()">
                <div class="modal-body">
                    <input type="hidden" name="command" value="order_without_recipe">
                    <input type="hidden" id="id" name="drugId">
                    <input type="hidden" id="price" name="price">
                    <input type="hidden" id="group" name="group">
                    <div class="form-group">
                        <label for="quantity">${quantity}:</label>
                        <p id="orderQuantityErr"></p>
                        <input type="number" class="form-control" id="quantity" name="quantity">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">${order}</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal_invalid-->
<div class="modal fade" id="modal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel2">${error}</h4>
            </div>
            <p align="center"><c:out value="${invalid}"/></p>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg" data-dismiss="modal">${close}</button>
                </div>
        </div>
    </div>
</div>
<!-- Modal_not_money-->
<div class="modal fade" id="modal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel3">${error}</h4>
            </div>
            <p align="center"><c:out value="${notMoney}"/></p>
            <div class="modal-footer">
                <button type="submit" class="btn-success btn-lg" data-dismiss="modal">${close}</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal_not_drugs-->
<div class="modal fade" id="modal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel4">${error}</h4>
            </div>
            <p align="center"><c:out value="${notDrugs}"/></p>
            <div class="modal-footer">
                <button type="submit" class="btn-success btn-lg" data-dismiss="modal">${close}</button>
            </div>
        </div>
    </div>
</div>
<script>
    $("#modal").on("show.bs.modal", function (event) {
        // получить кнопку, которая его открыло
        var button = $(event.relatedTarget);

        var id = button.data("id");
        var price = button.data("price");
        var group = button.data("group");
        var quantity = button.data("quantity");

        $(this).find("#id").val(id);
        $(this).find("#price").val(price);
        $(this).find("#group").val(group);

        document.querySelector("#quantity").setAttribute("data-max", quantity);
    });
</script>

<c:if test="${not empty requestScope.isValid}">
    <script>
        $(document).ready(function(){
            $("#modal2").modal('show');
        });
    </script>
</c:if>
<c:if test="${requestScope.execution eq 'money'}">
    <script>
        $(document).ready(function(){
            $("#modal3").modal('show');
        });
    </script>
</c:if>
<c:if test="${requestScope.execution eq 'quantity'}">
    <script>
        $(document).ready(function(){
            $("#modal4").modal('show');
        });
    </script>
</c:if>
</body>
</html>