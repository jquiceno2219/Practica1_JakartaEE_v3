<%--
  Created by IntelliJ IDEA.
  User: Jq64m
  Date: 18/09/2023
  Time: 2:55 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Map"%>
<%@ page import="com.example.practica1_jakartaee.mapping.dtos.TeacherDto" %>
<%@ page import="java.util.List" %>
<%
    Map<String,String> errorsmap =
            (Map<String,String>)request.getAttribute("errorsmap");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Subject Add Form</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h3><%= "Formulario materias" %></h3>
<%
    if(errorsmap != null && errorsmap.size()>0){
%>
<ul class="alert alert-danger mx-5">
    <% for(String error: errorsmap.values()){%>
    <li><%=error%></li>
    <%}%>
</ul>
<%}%>
<%
    List<TeacherDto> teachers = (List<TeacherDto>)getServletContext().getAttribute("teacherDtoList");
%>


<form action="subject-form" method="post">

    <div class="row mb-3">
        <label for="name" class="col-form-label col-sm-2">Name</label>
        <div class="col-sm-4"><input type="text" name="name" id="name" class="form-control"></div>
        <%
            if(errorsmap != null && errorsmap.containsKey("name")){
                out.println("<div class='row mb-3 alert alert-danger col-sm-4' style='color: red;'>"
                        + errorsmap.get("name") + "</div>");
            }
        %>
    </div>
    <div class="row mb-3">
        <%
            if(teachers != null && !teachers.isEmpty()){
        %>
        <label for="teacher-name" class="col-form-label col-sm-2">Teacher</label>
        <select id="teacher-name" name="teacher-name" class="form-select">
            <% for(TeacherDto var: teachers){%>
            <option><%=var.name()%></option>
            <%}%>
            <%}%>
        </select>
    </div>

    <div class="row mb-3">
        <div>
            <input type="submit" value="Enviar" class="btn btn-primary">
        </div>
    </div>
    <br/>
    <a href="student-form">Vamos a StudentController</a>
</form>
</body>
</html>
