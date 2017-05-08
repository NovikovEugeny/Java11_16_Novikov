<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../../css/bootstrap.css"/>
    <link rel="stylesheet" href="../../../css/main.css"/>
    <script src="../../../js/validator/signUpValidator.js"></script>
    <div id="local" data-item="${sessionScope.local}"></div>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="signup.title" var="sTitle"/>
    <fmt:message bundle="${loc}" key="signup.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="signup.name" var="name"/>
    <fmt:message bundle="${loc}" key="signup.patronymic" var="patronymic"/>
    <fmt:message bundle="${loc}" key="signin.mobilephone" var="mobilePhone"/>
    <fmt:message bundle="${loc}" key="signin.password" var="password"/>
    <fmt:message bundle="${loc}" key="signup.register" var="register"/>
    <fmt:message bundle="${loc}" key="signup.confirmpassword" var="confirmPassword"/>
    <fmt:message bundle="${loc}" key="title" var="title"/>
    <fmt:message bundle="${loc}" key="go.to.main" var="main"/>
    <fmt:message bundle="${loc}" key="invalid.name" var="invalidName"/>
    <fmt:message bundle="${loc}" key="invalid.mobile" var="invalidMobile"/>
    <fmt:message bundle="${loc}" key="invalid.password" var="invalidPassword"/>
    <fmt:message bundle="${loc}" key="invalid.confirm" var="invalidConfirm"/>
    <fmt:message bundle="${loc}" key="already.exists" var="alreadyExistsMessage"/>
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
            <div class="form-wrapper2">
                <div class="linker">
                    <span class="ring"></span>
                    <span class="ring"></span>
                    <span class="ring"></span>
                    <span class="ring"></span>
                    <span class="ring"></span>
                </div>
                <form action="controller" method="post" onsubmit="return validate()">
                    <input type="hidden" name="command" value="sign_up"/>
                    <p>
                        <c:if test="${requestScope.alreadyExists eq 'no'}">
                            <br>
                        </c:if>
                        <c:if test="${requestScope.alreadyExists eq 'yes'}">
                            <c:out value="${alreadyExistsMessage}"/>
                        </c:if>
                    </p>
                    <p id="surnameErr">
                        <c:if test="${empty requestScope.errorMap['invalidSurname']}">
                            <br>
                        </c:if>
                        <c:if test="${requestScope.errorMap['invalidSurname'] eq 'notValid'}">
                            <c:out value="${invalidName}"/>
                        </c:if>
                    </p>
                    <input type="text" name="surname" id="surname" placeholder="${surname}"/>
                    <p id="nameErr">
                        <c:if test="${empty requestScope.errorMap['invalidName']}">
                            <br>
                        </c:if>
                        <c:if test="${requestScope.errorMap['invalidName'] eq 'notValid'}">
                            <c:out value="${invalidName}"/>
                        </c:if>
                    </p>
                    <input type="text" name="name" id="name" placeholder="${name}"/>
                    <p id="patronymicErr">
                        <c:if test="${empty requestScope.errorMap['invalidPatronymic']}">
                            <br>
                        </c:if>
                        <c:if test="${requestScope.errorMap['invalidPatronymic'] eq 'notValid'}">
                            <c:out value="${invalidName}"/>
                        </c:if>
                    </p>
                    <input type="text" name="patronymic" id="patronymic" placeholder="${patronymic}"/>
                    <p id="mobileErr">
                        <c:if test="${empty requestScope.errorMap['invalidMobile']}">
                            <br>
                        </c:if>
                        <c:if test="${requestScope.errorMap['invalidMobile'] eq 'notValid'}">
                            <c:out value="${invalidMobile}"/>
                        </c:if>
                    </p>
                    <input type="text" name="mobile" id="mobile" placeholder="${mobilePhone}"/>
                    <p id="passwordErr">
                        <c:if test="${empty requestScope.errorMap['invalidPassword']}">
                            <br>
                        </c:if>
                        <c:if test="${requestScope.errorMap['invalidPassword'] eq 'notValid'}">
                            <c:out value="${invalidPassword}"/>
                        </c:if>
                    </p>
                    <input type="password" name="password" id="password" placeholder="${password}"/>
                    <p id="confirmErr">
                        <c:if test="${empty requestScope.errorMap['invalidConfirm']}">
                            <br>
                        </c:if>
                        <c:if test="${requestScope.errorMap['invalidConfirm'] eq 'notValid'}">
                            <c:out value="${invalidConfirm}"/>
                        </c:if>
                    </p>
                    <input type="password" name="confirm" id="confirm" placeholder="${confirmPassword}"/>
                    <button type="submit">${register}</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>