<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "¡Bienvenidos!" %>
</h1>
<br/>
<h2><%= "Estudiantes"%>
<a href="hello-servlet">Hello Estudiantes</a><br/>
<a href="student.jsp">Vamos a StudentForm</a><br/>
<a href="student-delete.jsp">Vamos a Borrar Estudiante</a><br/>

    <h2><%= "Subject"%>
<a href="subject.jsp">Vamos a SubjectForm</a><br/>

    <h2><%= "Teacher"%>
<a href="teacher.jsp">Vamos a teacherForm</a>
<br/>
<a href="login.jsp">Vamos a login</a>
<br/>
<a href="searchbyId.jsp">Ir a studentId</a>
</body>
</html>