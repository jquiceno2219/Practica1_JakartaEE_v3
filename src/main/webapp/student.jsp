<%--
  Created by IntelliJ IDEA.
  User: Jq64m
  Date: 18/09/2023
  Time: 8:29 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h3><%= "Formulario estudiantes" %>
</h3>

<form action="student-form" method="post">
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
<a href="subject-form">Vamos a SubjectController</a>
</body>
</html>
