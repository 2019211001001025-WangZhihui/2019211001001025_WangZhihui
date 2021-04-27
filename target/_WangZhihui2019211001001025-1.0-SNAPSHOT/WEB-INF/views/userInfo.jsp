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
//read cookies
    Cookie[] allCookies=request.getCookies();//all cookies
    for (Cookie c:allCookies){
        //get one by one
        out.println("<br/>"+c.getName()+" --- "+c.getValue());
    }
%>
<%
    User u=(User) session.getAttribute("user");
%>

<table>
    <tr><td>Username: </td><td><%=u.getUsername()%> </td></tr>

    <tr><td>Password: </td><td><%=u.getPassword()%> </td></tr>

    <tr><td>email: </td><td><%=u.getEmail()%> </td></tr>

    <tr><td>Gender: </td><td><%=u.getGender()%> </td></tr>

    <tr><td>BirthDate: </td><td><%=u.getBirthdate()%> </td></tr>

</table>
<%@include file="footer.jsp"%>