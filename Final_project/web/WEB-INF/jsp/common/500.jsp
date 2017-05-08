<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../../css/bootstrap.css" rel="stylesheet">
    <link href="../../../css/main.css" rel="stylesheet">
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="title" var="title"/>
    <fmt:message bundle="${loc}" key="go.to.main" var="toMain"/>
    <fmt:message bundle="${loc}" key="server.error.message" var="serverError"/>
    <title>500</title>
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
<section class="server-error">
    <p id="code">500</p>
    <p id="message">${serverError}</p>
</section>
</body>
</html>