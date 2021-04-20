<%@ page import="com.wangzhihui.model.User" %><%--
  Created by IntelliJ IDEA.
  User: 79805
  Date: 2021/4/12
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1>User Info</h1>
<%
    User user=(User)request.getAttribute("user");
%>

<table>
    <tr><td>Username: </td><td><%=user.getUsername()%> </td></tr>

    <tr><td>Password: </td><td><%=user.getPassword()%> </td></tr>

    <tr><td>email: </td><td><%=user.getEmail()%> </td></tr>

    <tr><td>Gender: </td><td><%=user.getGender()%> </td></tr>

    <tr><td>BirthDate: </td><td><%=user.getBirthdate()%> </td></tr>

</table>
<%@include file="footer.jsp"%>