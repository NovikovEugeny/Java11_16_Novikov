<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/main.css" rel="stylesheet">
    <script src="../../js/jquery-3.2.0.js"></script>
    <script src="../../js/bootstrap.js"></script>
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
                        <c:forEach var="element" items="${drugs}">
                            <tr>
                                <td><c:out value="${element.name}"/></td>
                                <td><c:out value="${element.group}"/></td>
                                <td><c:out value="${element.form}"/></td>
                                <td><c:out value="${element.drugAmount}"/></td>
                                <td><c:out value="${element.activeSubstances}"/></td>
                                <td><c:out value="${element.country}"/></td>
                                <td><c:out value="${element.dispensing}"/></td>
                                <td><c:out value="${element.price}"/></td>
                                <td><c:out value="${element.quantity}"/></td>
                                <td>
                                    <c:if test="${element.dispensing eq 'on prescription'}">
                                        <c:set var="modalId" value="#modalWith" scope="page"/>
                                    </c:if>
                                    <c:if test="${element.dispensing eq 'without prescription'}">
                                        <c:set var="modalId" value="#modalWithout" scope="page"/>
                                    </c:if>
                                    <button type="button" class="btn btn-success" data-toggle="modal"
                                            data-target="${modalId}" data-id="${element.id}"
                                            data-price="${element.price}"
                                            data-quantity="${element.quantity}"
                                            data-group="${element.group}">checkout
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

<!-- Modal without -->
<div class="modal fade" id="modalWithout" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel1">Форма заказа</h4>
            </div>
            <form action="controller" method="post">
                <div class="modal-body">
                    <input type="hidden" name="command" value="order_without_recipe">
                    <input type="hidden" id="withoutId" name="drugId">
                    <input type="hidden" id="withoutPrice" name="price">
                    <input type="hidden" id="withoutGroup" name="group">
                    <div class="form-group">
                        <label for="withoutQuantity">quantity:</label>
                        <input type="number" class="form-control" id="withoutQuantity" name="quantity" min="1">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">to order</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal with -->
<div class="modal fade" id="modalWith" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel2">Форма заказа</h4>
            </div>
            <form action="controller" method="post">
                <div class="modal-body">
                    <input type="hidden" name="command" value="order_with_recipe">
                    <input type="hidden" id="withId" name="drugId">
                    <input type="hidden" id="withPrice" name="price">
                    <input type="hidden" id="withGroup" name="group">
                    <div class="form-group">
                        <label for="withQuantity">quantity:</label>
                        <input type="number" class="form-control" id="withQuantity" name="quantity" min="1">
                    </div>
                    <div class="form-group">
                        <label for="code">recipe code:</label>
                        <input type="text" class="form-control" id="code" name="recipeCode">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">to order</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $("#modalWithout").on("show.bs.modal", function (event) {
        // получить кнопку, которая его открыло
        var button = $(event.relatedTarget);

        var id = button.data("id");
        var price = button.data("price");
        var group = button.data("group");

        var quantity = button.data("quantity");

        $(this).find("#withoutId").val(id);
        $(this).find("#withoutPrice").val(price);
        $(this).find("#withoutGroup").val(group);

        document.querySelector("#withoutQuantity").setAttribute("max", quantity);
    })

    $("#modalWith").on("show.bs.modal", function (event) {
        // получить кнопку, которая его открыло
        var button = $(event.relatedTarget);

        var id = button.data("id");
        var price = button.data("price");
        var group = button.data("group");

        var quantity = button.data("quantity");

        $(this).find("#withId").val(id);
        $(this).find("#withPrice").val(price);
        $(this).find("#withGroup").val(group);

        document.querySelector("#withQuantity").setAttribute("max", quantity);
    })
</script>

</body>
</html>
