<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/main.css" rel="stylesheet">
    <script src="../../js/jquery-3.2.0.js"></script>
    <script src="../../js/bootstrap.js"></script>
    <script src="../../js/drugUpdateValidator.js"></script>
    <title>drug list to remove</title>
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
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-3">
            <nav class="service-list">
                <form action="controller" method="get">
                    <input type="hidden" name="command"
                           value="pharmacist_show_order_list">
                    <button type="submit">Список заказов</button>
                </form>
                <form action="pharmacistGroupsToUpdate">
                    <button type="submit">Список репаратов</button>
                </form>
                <form action="pharmacistAddDrug">
                    <button type="submit">Добавить препарат</button>
                </form>
                <form>
                    <button type="submit">Отчет по продажам</button>
                </form>
            </nav>
        </div>
        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-9">
            <section>
                <h3>Список препаратов:</h3>
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
                                <td><c:out value="${element.dispensing}"/></td>
                                <td><c:out value="${element.price}"/></td>
                                <td><c:out value="${element.quantity}"/></td>
                                <td>
                                    <button type="button" class="btn btn-success" data-toggle="modal"
                                            data-target="#addModal" data-id="${element.id}" data-group="${element.group}">add
                                    </button>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success" data-toggle="modal"
                                            data-target="#removeModal" data-id="${element.id}" data-group="${element.group}">remove
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
<!-- Modal add -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel1">Добавление товара</h4>
            </div>
            <form action="controller" method="post" onsubmit="return validate()">
                <div class="modal-body">
                    <input type="hidden" name="command" value="add_drug_quantity">
                    <input type="hidden" id="a_id" name="id">
                    <input type="hidden" id="a_group" name="group">
                    <div class="form-group">
                        <label for="n">quantity:</label>
                        <p id="addDrugQuantityErr"></p>
                        <input type="number" class="form-control" id="n" name="quantity" min="1">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">add</button>
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
                <h4 class="modal-title" id="myModalLabel2">Удаление товара</h4>
            </div>
            <form action="controller" method="post">
                <div class="modal-body">
                    <input type="hidden" name="command" value="remove_drug">
                    <input type="hidden" id="r_id" name="id">
                    <input type="hidden" id="r_group" name="group">
                    Внимание, после подтверждения данный препарат будет
                    безвозвратно удален из системы!
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">remove</button>
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
