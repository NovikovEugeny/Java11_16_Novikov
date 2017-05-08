<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="doctor.title" var="title"/>
    <fmt:message bundle="${loc}" key="logout" var="logout"/>
    <fmt:message bundle="${loc}" key="application.list" var="pageTitle"/>
    <fmt:message bundle="${loc}" key="application.date" var="dateColumn"/>
    <fmt:message bundle="${loc}" key="recipe.code" var="recipeCodeColumn"/>
    <fmt:message bundle="${loc}" key="button.approve" var="buttonApprove"/>
    <fmt:message bundle="${loc}" key="button.deny" var="buttonDeny"/>
    <fmt:message bundle="${loc}" key="no.applications" var="noApplication"/>
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
    <c:if test="${not empty requestScope.recipeList}">
        <section class="recipe-list">
            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <h3>${pageTitle}</h3>
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>${dateColumn}</th>
                                    <th>${recipeCodeColumn}</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="element" items="${requestScope.recipeList}">
                                    <tr bgcolor="#FFFFFF">
                                        <td><fmt:formatDate value="${element.requestDate}" type="both"
                                                            dateStyle="medium" timeStyle="medium"/>
                                        </td>
                                        <td><c:out value="${element.recipeCode}"/></td>
                                        <td>
                                            <form action="controller" method="post">
                                                <input type="hidden" name="command" value="approve">
                                                <input type="hidden" name="id" value="${element.id}">
                                                <input type="hidden" name="recipeCode" value="${element.recipeCode}">
                                                <button type="submit"
                                                        class="btn-success btn-lg">${buttonApprove}</button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="controller" method="post">
                                                <input type="hidden" name="command" value="deny">
                                                <input type="hidden" name="id" value="${element.id}">
                                                <input type="hidden" name="recipeCode" value="${element.recipeCode}">
                                                <button type="submit" class="btn-success btn-lg">${buttonDeny}</button>
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
    </c:if>
</div>
<c:if test="${empty requestScope.recipeList}">
    <p align="center">${noApplication}</p>
</c:if>
</body>
</html>