<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/main.css" />" rel="stylesheet">
    <script src="<c:url value="/js/jquery-3.2.0.js" />"></script>
    <script src="<c:url value="/js/bootstrap.js" />"></script>
    <script src="<c:url value="/js/searchValidator.js" />"></script>
    <div id="local" data-item="${sessionScope.local}"></div>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="title" var="title"/>
    <fmt:message bundle="${loc}" key="label.language" var="lang"/>
    <fmt:message bundle="${loc}" key="button.signin" var="signIn"/>
    <fmt:message bundle="${loc}" key="button.signup" var="singUp"/>
    <fmt:message bundle="${loc}" key="button.search" var="search"/>
    <fmt:message bundle="${loc}" key="button.cardio" var="cardio"/>
    <fmt:message bundle="${loc}" key="button.antiviral" var="antiviral"/>
    <fmt:message bundle="${loc}" key="button.immunity" var="immunity"/>
    <fmt:message bundle="${loc}" key="button.antibiotics" var="antibiotics"/>
    <fmt:message bundle="${loc}" key="button.nerv" var="nerv"/>
    <fmt:message bundle="${loc}" key="button.allergy" var="allergy"/>
    <fmt:message bundle="${loc}" key="button.gastr" var="gastr"/>
    <fmt:message bundle="${loc}" key="button.antipyretics" var="antipyretics"/>
    <fmt:message bundle="${loc}" key="button.antiseptics" var="antiseptics"/>
    <fmt:message bundle="${loc}" key="button.lor" var="lor"/>
    <fmt:message bundle="${loc}" key="label.language" var="lang"/>
    <fmt:message bundle="${loc}" key="label.searctitle" var="searchTitle"/>
    <fmt:message bundle="${loc}" key="label.searchmessage" var="searchMessage"/>
    <fmt:message bundle="${loc}" key="label.info" var="info"/>
    <fmt:message bundle="${loc}" key="copyright" var="copyright"/>
    <fmt:message bundle="${loc}" key="search.invalid" var="searchError"/>
    <title>${title} ${sessionScope.local}</title>
</head>
<body>
<header>
    <div class="header-topline">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="lang">
                        ${lang}
                        <a href="controller?command=change_locale&local=ru">ru</a> /
                        <a href="controller?command=change_locale&local=en">en</a>
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
<div class="center-start">
    <div class="row">
        <div class="col-xs-6 col-sm-4 col-md-4 col-lg-3">
            <nav class="group-buttons">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs"/>
                    <input type="hidden" name="group" value="antibiotics"/>
                    <button type="submit">${antibiotics}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs"/>
                    <input type="hidden" name="group" value="allergy"/>
                    <button type="submit">${allergy}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs"/>
                    <input type="hidden" name="group" value="antipyretics"/>
                    <button type="submit">${antipyretics}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs"/>
                    <input type="hidden" name="group" value="antiviral"/>
                    <button type="submit">${antiviral}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs"/>
                    <input type="hidden" name="group" value="antiseptics"/>
                    <button type="submit">${antiseptics}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs"/>
                    <input type="hidden" name="group" value="cardiovascular"/>
                    <button type="submit">${cardio}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs"/>
                    <input type="hidden" name="group" value="immunity"/>
                    <button type="submit">${immunity}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs"/>
                    <input type="hidden" name="group" value="nervous system"/>
                    <button type="submit">${nerv}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs"/>
                    <input type="hidden" name="group" value="otolaryngology"/>
                    <button type="submit">${lor}</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs"/>
                    <input type="hidden" name="group" value="gastrointestinal"/>
                    <button type="submit">${gastr}</button>
                </form>
            </nav>
        </div>
        <div class="col-xs-6 col-sm-8 col-md-8 col-lg-9">
            <section class="search">
               <c:out value="${searchMessage}"/>
                <form action="controller" method="get" onsubmit="return validate()">
                    <input type="hidden" name="command" value="search">
                    <input type="text" name="drugName" id="drugName">
                    <button type="submit">${search}</button>
                </form>
                <p id="search-error">
                    <c:if test="${requestScope.isValid eq 'no'}">
                        <c:out value="${searchError}"/>
                    </c:if>
                </p>
            </section>
            <section class="info">
                <h3>${searchTitle}</h3>
                <p>${info}</p>
            </section>
        </div>
    </div>
</div>
<footer>
    <div class="row">
        <p>${copyright}</p>
    </div>
</footer>
</body>
</html>