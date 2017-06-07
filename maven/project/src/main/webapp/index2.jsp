<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <script src="js/jquery-3.2.0.js"></script>
    <title>index2</title>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="loc" var="loc"/>
    <fmt:message bundle="${loc}" key="button.send" var="send"/>
</head>
<body>
<input type="text" id="message" name="message">
<button onclick="send()">${send}</button>
<script>
    function send() {
        $.ajax({
            async: true,
            cache: false,
            url: '/controller',
            method: "post",
            data: {'message': $("#message").val()},
            error: function () {
                alert("error");
            },
            success: function (data) {
                alert(data);
            }
        });
    }
</script>
</body>
</html>