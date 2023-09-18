<%--
  Created by IntelliJ IDEA.
  User: Jq64m
  Date: 18/09/2023
  Time: 2:55 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3><%= "Formulario materias" %>
</h3>
<form action="subject-form" method="post">
    <div class="row mb-3">
        <label for="name" class="col-form-label col-sm-2">Name</label>
        <div class="col-sm-4"><input type="text" name="name" id="name" class="form-control"></div>
    </div>
    <div class="row mb-3">
        <label for="career" class="col-form-label col-sm-2">Career</label>
        <div class="col-sm-4"><input type="text" name="career" id="career" class="form-control"></div>
    </div>
    <div class="row mb-3">
        <div>
            <input type="submit" value="Enviar" class="btn btn-primary">
        </div>
    </div>
</form>
<br/>
<a href="student-form">Vamos a StudentController</a>
</body>
</html>
