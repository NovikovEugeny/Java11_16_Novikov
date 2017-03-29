<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 26.02.2017
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>show order</title>
</head>
<body>

<table border="0" cellspacing="1" bgcolor="#000000">
    <tr bgcolor="#FFFFFF">
        <th>name</th>
        <th>group</th>
        <th>form</th>
        <th>amount</th>
        <th>active substances</th>
        <th>country</th>
        <th>quantity</th>
        <td>click</td>
    </tr>
        <tr bgcolor="#FFFFFF">
            <td><c:out value="${drug.name}"/></td>
            <td><c:out value="${drug.group}"/></td>
            <td><c:out value="${drug.form}"/></td>
            <td><c:out value="${drug.drugAmount}"/></td>
            <td><c:out value="${drug.activeSubstances}"/></td>
            <td><c:out value="${drug.country}"/></td>
            <td><c:out value="${drug.quantity}"/></td>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="orderId" value="${orderId}">
                    <input type="hidden" name="command" value="send">
                    <input type="submit" value="send">
                </form>
            </td>
        </tr>
</table>

</body>
</html>
