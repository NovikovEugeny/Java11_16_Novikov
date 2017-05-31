<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <script src="../../../js/jquery-3.2.0.js"></script>
    <script src="../../../js/bootstrap.js"></script>
    <script src="../../../js/validator/signUpValidator.js"></script>
    <div id="local" data-item="${sessionScope.local}"></div>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="admin.title" var="title"/>
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
    <fmt:message bundle="${loc}" key="admin.pharmacists" var="pharmacists"/>
    <fmt:message bundle="${loc}" key="admin.doctors" var="doctors"/>
    <fmt:message bundle="${loc}" key="admin.remove" var="remove"/>
    <fmt:message bundle="${loc}" key="admin.register" var="register"/>
    <fmt:message bundle="${loc}" key="admin.removal" var="removal"/>
    <fmt:message bundle="${loc}" key="admin.registration" var="registration"/>
    <fmt:message bundle="${loc}" key="signup.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="signup.name" var="name"/>
    <fmt:message bundle="${loc}" key="signup.patronymic" var="patronymic"/>
    <fmt:message bundle="${loc}" key="signin.mobilephone" var="mobile"/>
    <fmt:message bundle="${loc}" key="signin.password" var="password"/>
    <fmt:message bundle="${loc}" key="signup.confirmpassword" var="confirm"/>
    <fmt:message bundle="${loc}" key="admin.pharmacist" var="pharmacist"/>
    <fmt:message bundle="${loc}" key="admin.doctor" var="doctor"/>
    <fmt:message bundle="${loc}" key="admin.position" var="position"/>
    <fmt:message bundle="${loc}" key="admin.add" var="add"/>
    <fmt:message bundle="${loc}" key="are.you.sure" var="ays"/>
    <fmt:message bundle="${loc}" key="noscript" var="noscript"/>
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
    <div class="container">
        <div class="row">
            <noscript>
                <p align="center">${noscript}</p>
            </noscript>
            <section class="employee-data">
                <div class="col-md-6">
                    <h3>${pharmacists}:</h3>
                    <ul class="list-group">
                        <c:forEach var="element" items="${requestScope.employeeList}">
                            <c:if test="${element.position eq 'pharmacist'}">
                                <li class="list-group-item">
                                        ${element.surname} ${element.name} ${element.patronymic} ${element.mobilePhone}
                                    <button type="button" class="btn btn-success btn-lg" data-toggle="modal"
                                            data-target="#removeModal" data-id="${element.id}">${remove}
                                    </button>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-6">
                    <h3>${doctors}:</h3>
                    <ul class="list-group">
                        <c:forEach var="element" items="${requestScope.employeeList}">
                            <c:if test="${element.position eq 'doctor'}">
                                <li class="list-group-item">
                                        ${element.surname} ${element.name} ${element.patronymic} ${element.mobilePhone}
                                    <button type="button" class="btn btn-success btn-lg" data-toggle="modal"
                                            data-target="#removeModal" data-id="${element.id}">${remove}
                                    </button>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </section>
        </div>
        <div class="row">
            <button type="button" class="btn btn-success btn-lg" data-toggle="modal"
                    data-target="#registerModal" id="register-employee">${register}
            </button>
        </div>
    </div>
</div>
<!--removeModal-->
<div class="modal fade" id="removeModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel2">${removal}</h4>
            </div>
            <form action="controller" method="post">
                <div class="modal-body">
                    <c:out value="${ays}?"/>
                    <input type="hidden" name="command" value="remove_employee">
                    <input type="hidden" id="id" name="id">
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">${remove}</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!--registerModal-->
<div class="modal fade" id="registerModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" id="admin-add-modal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel3">${registration}</h4>
            </div>
            <form action="controller" method="post" onsubmit="return validate()">
                <div class="modal-body">
                    <input type="hidden" name="command" value="add_employee">
                    <div class="employee-registration">
                        <div class="form-group">
                            <label for="position">${position}:</label>
                            <select class="form-control" id="position" name="position">
                                <option value="doctor">${doctor}</option>
                                <option value="pharmacist">${pharmacist}</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="surname">${surname}:</label>
                            <p id="surnameErr"></p>
                            <input type="text" class="form-control" id="surname" name="surname">
                        </div>
                        <div class="form-group">
                            <label for="name">${name}:</label>
                            <p id="nameErr"></p>
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                        <div class="form-group">
                            <label for="patronymic">${patronymic}:</label>
                            <p id="patronymicErr"></p>
                            <input type="text" class="form-control" id="patronymic" name="patronymic">
                        </div>
                        <div class="form-group">
                            <label for="mobile">${mobile}:</label>
                            <p id="mobileErr"></p>
                            <input type="text" class="form-control" id="mobile" name="mobile">
                        </div>
                        <div class="form-group">
                            <label for="password">${password}:</label>
                            <p id="passwordErr"></p>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                        <div class="form-group">
                            <label for="confirm">${confirm}:</label>
                            <p id="confirmErr"></p>
                            <input type="password" class="form-control" id="confirm" name="confirm">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-success btn-lg">${add}</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $("#removeModal").on("show.bs.modal", function (event) {
        // получить кнопку, которая его открыло
        var button = $(event.relatedTarget);
        var id = button.data('id');
        $(this).find("#id").val(id);
    });
</script>
</body>
</html>