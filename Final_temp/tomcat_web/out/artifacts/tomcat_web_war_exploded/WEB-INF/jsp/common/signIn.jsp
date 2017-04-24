<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="../../../css/main.css" rel="stylesheet">
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <script src="../../../js/signInValidator.js"></script>
    <div id="local" data-item="${sessionScope.local}"></div>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="signin.title" var="sTitle"/>
    <fmt:message bundle="${loc}" key="signin.mobilephone" var="mobilePhone"/>
    <fmt:message bundle="${loc}" key="signin.password" var="password"/>
    <fmt:message bundle="${loc}" key="signin.enter" var="enter"/>
    <fmt:message bundle="${loc}" key="title" var="title"/>
    <fmt:message bundle="${loc}" key="go.to.main" var="main"/>
    <fmt:message bundle="${loc}" key="empty.mobile" var="emptyMobileMessage"/>
    <fmt:message bundle="${loc}" key="empty.password" var="emptyPasswordMessage"/>
    <fmt:message bundle="${loc}" key="not.exists" var="notExistsMessage"/>
    <title>${sTitle}</title>
</head>
<body>
<header>
    <div class="header-topline">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <a href="controller?command=start_page">${main}</a>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="row">
    <div class="col-md-4">
        <div class="logo">
            <img src="../../../images/logo.jpg">
            <h1>${title}</h1>
        </div>
    </div>
    <div class="col-md-4">
        <div class="content">
            <div class="form-wrapper">
                <div class="linker">
                    <span class="ring"></span>
                    <span class="ring"></span>
                    <span class="ring"></span>
                    <span class="ring"></span>
                    <span class="ring"></span>
                </div>
                <form action="controller" method="post" onsubmit="return validate()">
                    <input type="hidden" name="command" value="sign_in"/>
                    <p>
                        <c:if test="${empty requestScope.isExists}">
                            <br>
                        </c:if>
                        <c:if test="${not empty requestScope.isExists}">
                            <c:out value="${notExistsMessage}"/>
                        </c:if>
                    </p>
                    <p id="mobileErr">
                        <c:if test="${empty requestScope.errorMap['invalidMobile']}">
                            <br>
                        </c:if>
                        <c:if test="${requestScope.errorMap['invalidMobile'] eq 'notValid'}">
                            <c:out value="${emptyMobileMessage}"/>
                        </c:if>
                    </p>
                    <input type="text" name="mobile" id="mobile" placeholder="${mobilePhone}"/>
                    <p id="passwordErr">
                        <c:if test="${empty requestScope.errorMap['invalidPassword']}">
                            <br>
                        </c:if>
                        <c:if test="${requestScope.errorMap['invalidPassword'] eq 'notValid'}">
                            <c:out value="${emptyPasswordMessage}"/>
                        </c:if>
                    </p>
                    <input type="password" name="password" id="password" placeholder="${password}"/>
                    <button type="submit">${enter}</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>