<%--
  Created by IntelliJ IDEA.
  User: Jq64m
  Date: 2/10/2023
  Time: 3:04 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Borrar Estudiante</title>
</head>
<body>
<h3><%= "Delete student" %>
</h3>

<form action="student-form" method="post">
  <div class="row mb-3">
    <label for="student-id" class="col-form-label col-sm-2">Id</label>
    <div class="col-sm-4"><input type="text" name="student-id" id="student-id" class="form-control"></div>
  </div>
  <div class="row mb-3">
    <div>
      <input type="submit" value="Eliminar" class="btn btn-primary">
    </div>
  </div>
</form>
<br/>
</body>
</html>
