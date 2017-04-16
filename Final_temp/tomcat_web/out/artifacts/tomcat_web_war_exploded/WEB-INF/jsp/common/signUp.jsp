<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../../css/bootstrap.css"/>
    <link rel="stylesheet" href="../../../css/main.css"/>
    <script src="../../../js/signUpValidator.js"></script>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="signup.title" var="title"/>
    <fmt:message bundle="${loc}" key="signup.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="signup.name" var="name"/>
    <fmt:message bundle="${loc}" key="signup.patronymic" var="patronymic"/>
    <fmt:message bundle="${loc}" key="signin.mobilephone" var="mobilePhone"/>
    <fmt:message bundle="${loc}" key="signin.password" var="password"/>
    <fmt:message bundle="${loc}" key="signup.register" var="register"/>
    <fmt:message bundle="${loc}" key="signup.confirmpassword" var="confirmPassword"/>
    <title>${title}</title>
</head>
<body>
<header>
    <div class="header-topline">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <a href="controller?command=start_page">на главную</a>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="row">
    <div class="col-md-4">
        <div class="logo">
            <img src="../../../images/logo.jpg">
            <h1>Онлайн-аптека</h1>
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
                    <p><c:out value="${requestScope.errorMessage}"/></p>
                    <p id="surnameErr"><br><c:out value="${requestScope.errorMap['invalidSurname']}"/></p>
                    <input type="text" name="surname" id="surname" placeholder="${surname}"/>
                    <p id="nameErr"><br><c:out value="${requestScope.errorMap['invalidName']}"/></p>
                    <input type="text" name="name" id="name" placeholder="${name}"/>
                    <p id="patronymicErr"><br><c:out value="${requestScope.errorMap['invalidPatronymic']}"/></p>
                    <input type="text" name="patronymic" id="patronymic" placeholder="${patronymic}"/>
                    <p id="mobileErr"><br><c:out value="${requestScope.errorMap['invalidMobile']}"/></p>
                    <input type="text" name="mobile" id="mobile" placeholder="${mobilePhone}"/>
                    <p id="passwordErr"><br><c:out value="${requestScope.errorMap['invalidPassword']}"/></p>
                    <input type="password" name="password" id="password" placeholder="${password}"/>
                    <p id="confirmErr"><br><c:out value="${requestScope.errorMap['invalidConfirm']}"/></p>
                    <input type="password" name="confirm" id="confirm" placeholder="${confirmPassword}"/>
                    <button type="submit">${register}</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
