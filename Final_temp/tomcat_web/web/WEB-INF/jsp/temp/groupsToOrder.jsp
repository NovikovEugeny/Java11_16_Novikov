<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="button.cardio" var="cardio"/>
    <fmt:message bundle="${loc}" key="button.antiviral" var="a_v"/>
    <fmt:message bundle="${loc}" key="button.immunity" var="immunity"/>
    <fmt:message bundle="${loc}" key="button.antibiotics" var="antibio"/>
    <fmt:message bundle="${loc}" key="button.nerv" var="nerv"/>
    <fmt:message bundle="${loc}" key="button.allergy" var="allergy"/>
    <fmt:message bundle="${loc}" key="button.gastr" var="gastr"/>
    <fmt:message bundle="${loc}" key="button.antipyretics" var="antipyr"/>
    <title>Title</title>
</head>
<body>
<form action="controller" method="get">
    <input type="hidden" name="command" value="show_drugs_to_order"/>
    <input type="hidden" name="group" value="allergy"/>
    <input type="submit" value="${allergy}"/>
</form>

<form action="controller" method="get">
    <input type="hidden" name="command" value="show_drugs_to_order"/>
    <input type="hidden" name="group" value="antibiotics"/>
    <input type="submit" value="${antibio}"/>
</form>

<form action="controller" method="get">
    <input type="hidden" name="command" value="show_drugs_to_order"/>
    <input type="hidden" name="group" value="antiviral"/>
    <input type="submit" value="${a_v}"/>
</form>

<form action="controller" method="get">
    <input type="hidden" name="command" value="show_drugs_to_order"/>
    <input type="hidden" name="group" value="antipyretics"/>
    <input type="submit" value="${antipyr}"/>
</form>

<form action="controller" method="get">
    <input type="hidden" name="command" value="show_drugs_to_order"/>
    <input type="hidden" name="group" value="cardiovascular"/>
    <input type="submit" value="${cardio}"/>
</form>

<form action="controller" method="get">
    <input type="hidden" name="command" value="show_drugs_to_order"/>
    <input type="hidden" name="group" value="gastrointestinal"/>
    <input type="submit" value="${gastr}"/>
</form>

<form action="controller" method="get">
    <input type="hidden" name="command" value="show_drugs_to_order"/>
    <input type="hidden" name="group" value="immunity"/>
    <input type="submit" value="${immunity}"/>
</form>

<form action="controller" method="get">
    <input type="hidden" name="command" value="show_drugs_to_order"/>
    <input type="hidden" name="group" value="nervous system"/>
    <input type="submit" value="${nerv}"/>
</form>
</body>
</html>
