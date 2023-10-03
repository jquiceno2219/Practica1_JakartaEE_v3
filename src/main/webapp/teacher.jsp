<%--
  Created by IntelliJ IDEA.
  User: Jq64m
  Date: 18/09/2023
  Time: 2:56 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Map"%>
<%
  Map<String,String> errorsmap =
          (Map<String,String>)request.getAttribute("errorsmap");
%>
<html>
<head>
    <title>Teacher Add Form</title>
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h3><%= "Formulario teachers" %></h3>
<%
  if(errorsmap != null && errorsmap.size()>0){
%>
<ul class="alert alert-danger mx-5">
  <% for(String error: errorsmap.values()){%>
  <li><%=error%></li>
  <%}%>
</ul>
<%}%>
<form action="teacher-form" method="post">
  <div class="row mb-3">
    <label for="teacher-name" class="col-form-label col-sm-2">Name</label>
    <div class="col-sm-4"><input type="text" name="teacher-name" id="teacher-name" class="form-control"></div>
    <%
      if(errorsmap != null && errorsmap.containsKey("name")){
        out.println("<div class='row mb-3 alert alert-danger col-sm-4' style='color: red;'>"
                + errorsmap.get("name") + "</div>");
      }
    %>
  </div>
  <div class="row mb-3">
    <label for="teacher-email" class="col-form-label col-sm-2">Email</label>
    <div class="col-sm-4"><input type="text" name="teacher-email" id="teacher-email" class="form-control"></div>
    <%
      if(errorsmap != null && errorsmap.containsKey("email")){
        out.println("<div class='row mb-3 alert alert-danger col-sm-4' style='color: red;'>"
                + errorsmap.get("email") + "</div>");
      }
    %>
  </div>
  <div class="row mb-3">
    <div>
      <input type="submit" value="Add" class="btn btn-primary">
    </div>
  </div>
</form>
<br/>
<a href="teacher-form">Vamos a TeacherController</a>
</body>
</html>
