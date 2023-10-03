<%--
  Created by IntelliJ IDEA.
  User: Jq64m
  Date: 3/10/2023
  Time: 11:17 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Map"%>
<%@ page import="com.example.practica1_jakartaee.mapping.dtos.SubjectDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.practica1_jakartaee.mapping.dtos.StudentDto" %>
<%
    Map<String,String> errorsmap =
            (Map<String,String>)request.getAttribute("errorsmap");
%>
<html>
<head>
    <title>Grades Add Form</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h3><%= "Formulario grades" %></h3>
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
    List<SubjectDto> subject = (List<SubjectDto>)getServletContext().getAttribute("subjectDtoList");
%>

<%
    List<StudentDto> students = (List<StudentDto>)getServletContext().getAttribute("studentDtoList");
%>

<form action="grades-form" method="post">


    <div class="row mb-3">
        <label for="grade" class="col-form-label col-sm-2">Grade</label>
        <div class="col-sm-4"><input type="text" name="grade" id="grade" class="form-control"></div>
        <%
            if(errorsmap != null && errorsmap.containsKey("grade")){
                out.println("<div class='row mb-3 alert alert-danger col-sm-4' style='color: red;'>"
                        + errorsmap.get("grade") + "</div>");
            }
        %>
    </div>
    <div class="row mb-3">
        <%
            if(subject != null && !subject.isEmpty()){
        %>
        <label for="subject-name" class="col-form-label col-sm-2">Subject</label>
        <select id="subject-name" name="subject-name" class="form-select">
            <% for(SubjectDto var: subject){%>
            <option><%=var.name()%></option>
            <%}%>
            <%}%>
        </select>
    </div>
    <div class="row mb-3">
        <%
            if(students != null && !students.isEmpty()){
        %>
        <label for="student-name" class="col-form-label col-sm-2">Student</label>
        <select id="student-name" name="student-name" class="form-select">
            <% for(StudentDto var: students){%>
            <option><%=var.name()%></option>
            <%}%>
            <%}%>
        </select>
    </div>
    <div class="row mb-3">
        <label for="term" class="col-form-label col-sm-2">Term</label>
        <div class="col-sm-4"><input type="text" name="term" id="term" class="form-control"></div>
        <%
            if(errorsmap != null && errorsmap.containsKey("term")){
                out.println("<div class='row mb-3 alert alert-danger col-sm-4' style='color: red;'>"
                        + errorsmap.get("term") + "</div>");
            }
        %>
    </div>
    <div class="row mb-3">
        <div>
            <input type="submit" value="Enviar" class="btn btn-primary">
        </div>
    </div>
</form>
</body>
</html>
