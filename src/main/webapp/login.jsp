<%--
  Created by IntelliJ IDEA.
  User: Jq64m
  Date: 20/09/2023
  Time: 8:52 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP - Login Form</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<form action="login" method="post">
    <div style="padding: 10px;">
        <div>
        <label for="username">Username</label>
            <div>
            <input type="text" name="username" id="username">
            </div>
        </div>
        <div>
        <label for="password">Password</label>
            <div>
            <input type="password" name="password" id="password">
            </div>
        </div>
        <div>
        <input type="submit" value="Login" class="btn btn-outline-primary">
        </div>
    </div>
</form>

</body>
</html>