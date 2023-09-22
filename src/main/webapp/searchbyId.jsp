<%--
  Created by IntelliJ IDEA.
  User: Jq64m
  Date: 21/09/2023
  Time: 11:54 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search By Id</title>
</head>
<body>
<form action="studentId" method="post">
  <div>
    <label for="id">Insert Id</label>
    <div>
      <input type="text" name="id" id="id">
    </div>
  </div>
  <div>
    <label for="password">Password</label>
    <div>
      <input type="password" name="password" id="password">
    </div>
  </div>
  <div>
    <input type="submit" value="Login">
  </div>
</form>
</body>
</html>
