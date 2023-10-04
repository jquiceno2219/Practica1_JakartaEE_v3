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
<a href="login.jsp">Login</a><br/>
<h2><%= "Estudiantes"%></h2>
<a href="hello-servlet">Hello Estudiantes</a><br/>
<a href="student.jsp">Vamos a StudentForm</a><br/>
<a href="student-delete.jsp">Vamos a Borrar Estudiante</a><br/>

<h2><%= "Subject"%></h2>
<a href="subject.jsp">Vamos a SubjectForm</a><br/>

<h2><%= "Teacher"%></h2>
<a href="teacher.jsp">Vamos a teacherForm</a>

<h2><%= "Grades"%></h2>
<a href="grades.jsp">Vamos a gradesForm (ahora si funciona)</a>

<br/>

<h2><%= "Otros"%></h2>
<a href="login.jsp">Vamos a login</a>
<br/>
<a href="searchbyId.jsp">Ir a studentId</a>

</body>
</html>