<%--
  Created by IntelliJ IDEA.
  User: 79805
  Date: 2021/4/6
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1>Login</h1>
<%
    if(!(request.getAttribute("massage")==null)){
        out.print("<h3>"+request.getAttribute("massage")+"</h3>");
    }
%>
<form method="post" action="login">
    <%= "Login" %><br/>
    Username<input type="text" name="username"><br/>
    Password<input type="password" name="password"><br/>
    <input type="submit" value="Submit"/>

</form>
<%@include file="footer.jsp"%>
