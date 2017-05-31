<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <style>
        #addDrugQuantityErr  {
            color: red;
        }
    </style>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <script src="../../../js/jquery-3.2.0.js"></script>
    <script src="../../../js/bootstrap.js"></script>
    <script src="../../../js/validator/drugQuantityValidator.js"></script>
    <div id="local" data-item="${sessionScope.local}"></div>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="pharmacist.drugs" var="title"/>
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
    <fmt:message bundle="${loc}" key="order.list" var="orderList"/>
    <fmt:message bundle="${loc}" key="drugs.list" var="drugList"/>
    <fmt:message bundle="${loc}" key="drug.add" var="addDrug"/>
    <fmt:message bundle="${loc}" key="sales.report" var="salesReport"/>
    <fmt:message bundle="${loc}" key="drugName" var="drugName"/>
    <fmt:message bundle="${loc}" key="drugGroup" var="drugGroup"/>
    <fmt:message bundle="${loc}" key="drugForm" var="drugForm"/>
    <fmt:message bundle="${loc}" key="drugAmount" var="drugAmount"/>
    <fmt:message bundle="${loc}" key="drugAS" var="AS"/>
    <fmt:message bundle="${loc}" key="country" var="country"/>
    <fmt:message bundle="${loc}" key="dispensing" var="dispnsing"/>
    <fmt:message bundle="${loc}" key="price" var="price"/>
    <fmt:message bundle="${loc}" key="quantity" var="quantity"/>
    <fmt:message bundle="${loc}" key="removal" var="drugRemoval"/>
    <fmt:message bundle="${loc}" key="add" var="add"/>
    <fmt:message bundle="${loc}" key="remove" var="remove"/>
    <fmt:message bundle="${loc}" key="addititon" var="addition"/>
    <fmt:message bundle="${loc}" key="remove.message" var="removeMessage"/>
    <fmt:message bundle="${loc}" key="drug.update.quantity.error.message" var="updateDrugErrorMessage"/>
    <fmt:message bundle="${loc}" key="noscript" var="nosript"/>
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
        <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
            <nav class="service-list">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="pharmacist_show_order_list">
                    <button type="submit">${orderList}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="groups_to_update_page">
                    <button type="submit">${drugList}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="add_drug_page">
                    <button type="submit">${addDrug}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_sales_report">
                    <button type="submit">${salesReport}</button>
                </form>
            </nav>
        </div>
        <div class="col-xs-12 col-sm-9 col-md-9 col-lg-9">
            <noscript>
                <p align="center">${nosript}</p>
            </noscript>
            <section class="drugs-to-update">
                <h3>${drugList}:</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>${drugName}</th>
                            <th>${drugGroup}</th>
                            <th>${drugForm}</th>
                            <th>${drugAmount}</th>
                            <th>${AS}</th>
                            <th>${country}</th>
                            <th>${dispnsing}</th>
                            <th>${price}</th>
                            <th>${quantity}</th>
                            <th></th>
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
                                            data-target="#addModal" data-id="${element.id}"
                                            data-group="${element.group}">${add}
                                    </button>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success" data-toggle="modal"
                                            data-target="#removeModal" data-id="${element.id}"
                                            data-group="${element.group}">${remove}
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
<c:if test="${not empty requestScope.isValid}">
    <script>
        alert("${updateDrugErrorMessage}");
    </script>
</c:if>
<!-- Modal add -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel1">${addition}</h4>
            </div>
            <form action="controller" method="post" onsubmit="return validate()">
                <div class="modal-body">
                    <input type="hidden" name="command" value="add_drug_quantity">
                    <input type="hidden" id="a_id" name="id">
                    <input type="hidden" id="a_group" name="group">
                    <div class="form-group">
                        <label for="n">${quantity}:</label>
                        <p id="addDrugQuantityErr"></p>
                        <input type="number" class="form-control" id="n" name="quantity">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">${add}</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal remove -->
<div class="modal fade" id="removeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel2">${drugRemoval}</h4>
            </div>
            <form action="controller" method="post">
                <div class="modal-body">
                    <input type="hidden" name="command" value="remove_drug">
                    <input type="hidden" id="r_id" name="id">
                    <input type="hidden" id="r_group" name="group">
                    ${removeMessage}
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">${remove}</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $('#addModal').on('show.bs.modal', function (event) {
        // получить кнопку, которая его открыло
        var button = $(event.relatedTarget);
        // извлечь информацию из атрибута data-content
        var id = button.data('id');
        var group = button.data('group');
        // вывести эту информацию в элемент, имеющий id="content"
        $(this).find('#a_id').val(id);
        $(this).find('#a_group').val(group);
    })

    $('#removeModal').on('show.bs.modal', function (event) {
        // получить кнопку, которая его открыло
        var button = $(event.relatedTarget);
        // извлечь информацию из атрибута data-content
        var id = button.data('id');
        var group = button.data('group');

        $(this).find('#r_id').val(id);
        $(this).find('#r_group').val(group);
    })
</script>
</body>
</html>