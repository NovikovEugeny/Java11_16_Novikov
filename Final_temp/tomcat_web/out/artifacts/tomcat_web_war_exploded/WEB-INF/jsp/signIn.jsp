<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="../../css/signIn.css" rel="stylesheet">
    <script src="../../js/signInValidator.js"></script>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="signin.title" var="title"/>
    <fmt:message bundle="${loc}" key="signin.mobilephone" var="mobilePhone"/>
    <fmt:message bundle="${loc}" key="signin.password" var="password"/>
    <fmt:message bundle="${loc}" key="signin.enter" var="enter"/>
    <title>${title}</title>
</head>
<body>
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
                <p>${requestScope.errorMessage}</p>
                <p id="mobileErr"><br><c:out value="${requestScope.errorMap['invalidMobile']}"/></p>
                <input type="text" name="mobile" id="mobile" placeholder="${mobilePhone}"/>
                <p id="passwordErr"><br><c:out value="${requestScope.errorMap['invalidPassword']}"/></p>
                <input type="password" name="password" id="password" placeholder="${password}"/>
                <button type="submit">${enter}</button>
            </form>
        </div>
    </div>
</body>
</html>