<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="title" var="title"/>
    <fmt:message bundle="${loc}" key="button.signin" var="signIn"/>
    <fmt:message bundle="${loc}" key="button.signup" var="singUp"/>
    <fmt:message bundle="${loc}" key="go.to.main" var="toMain"/>
    <fmt:message bundle="${loc}" key="druglist.title" var="drugListTitle"/>
    <fmt:message bundle="${loc}" key="druglist.pagetitle" var="drugListPageTitle"/>
    <fmt:message bundle="${loc}" key="nothing.message" var="nothing"/>
    <fmt:message bundle="${loc}" key="drugName" var="name"/>
    <fmt:message bundle="${loc}" key="drugGroup" var="group"/>
    <fmt:message bundle="${loc}" key="drugForm" var="form"/>
    <fmt:message bundle="${loc}" key="drugAmount" var="amount"/>
    <fmt:message bundle="${loc}" key="drugAS" var="AS"/>
    <fmt:message bundle="${loc}" key="country" var="country"/>
    <fmt:message bundle="${loc}" key="dispensing" var="dispensing"/>
    <fmt:message bundle="${loc}" key="price" var="price"/>
    <fmt:message bundle="${loc}" key="quantity" var="quantity"/>
    <fmt:message bundle="${loc}" key="on" var="on"/>
    <fmt:message bundle="${loc}" key="without" var="without"/>
    <title>${drugListTitle}</title>
</head>
<body>
<header>
    <div class="header-topline">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="goto-main">
                        <a href="controller?command=start_page">${toMain}</a>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="auth">
                        <a href="controller?command=sign_in_page">${signIn}</a> /
                        <a href="controller?command=sign_up_page">${singUp}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="logo">
        <div class="row">
            <div class="col-md-12">
                <img src="../../../images/logo.jpg">
                <h1>${title}</h1>
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
                    <h3>${drugListPageTitle}:</h3>

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
    <p align="center">${nothing}</p>
</c:if>
</body>
</html>