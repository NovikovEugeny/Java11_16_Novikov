<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>orderFormWith</title>
</head>
<body>

<form action="controller" method="post">
    <input type="hidden" name="command" value="order_with_recipe">
    <input type="hidden" name="drugId" value="${drugId}">
    <input type="hidden" name="price" value="${price}">

    <label>quantity</label>
    <input type="text" name="quantity">

    <label>delivery address</label>
    <input type="text" name="deliveryAddress">

    <label>recipe code</label>
    <input type="text" name="code">

    <input type="submit" value="order">
</form>

</body>
</html>
