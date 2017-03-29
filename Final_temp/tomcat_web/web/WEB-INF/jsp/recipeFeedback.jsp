<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 11.03.2017
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>recipe feedback</title>
</head>
<body>



<form action="controller" method="post">

    <textarea rows="5" cols="20" name="feedback"></textarea>

    <input type="hidden" name="recipeId" value="${recipeId}">
    <input type="hidden" name="command" value="send_feedback">
    <input type="submit" value="send feedback">
</form>

</body>
</html>
