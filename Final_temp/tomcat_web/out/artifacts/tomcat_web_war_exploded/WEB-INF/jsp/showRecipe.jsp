<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 05.03.2017
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>show recipe</title>
</head>
<body>

<table border="0" cellspacing="1" bgcolor="#000000">
    <tr bgcolor="#FFFFFF">
        <th>drug name</th>
        <th>pharm. group</th>
        <th>drug form</th>
        <th>drug amount</th>
        <th>active substances</th>
        <th>country</th>
        <th>quantity</th>
        <th>recipe code</th>
        <td>click</td>
        <td>click</td>
    </tr>
    <tr bgcolor="#FFFFFF">
        <td><c:out value="${name}"/></td>
        <td><c:out value="${group}"/></td>
        <td><c:out value="${form}"/></td>
        <td><c:out value="${drugAmount}"/></td>
        <td><c:out value="${activeSubstances}"/></td>
        <td><c:out value="${country}"/></td>
        <td><c:out value="${quantity}"/></td>
        <td><c:out value="${recipeCode}"/></td>
        <td>
            <form action="controller" method="post">
                <input type="hidden" name="recipeId" value="${recipeId}">
                <input type="hidden" name="command" value="approve">
                <input type="submit" value="approve">
            </form>
        </td>
        <td>
            <form action="controller" method="post">
                <input type="hidden" name="recipeId" value="${recipeId}">
                <input type="hidden" name="command" value="deny">
                <input type="submit" value="deny">
            </form>
        </td>
    </tr>
</table>

</body>
</html>
