<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: 79805
  Date: 2021/4/12
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<%@ page import="java.sql.ResultSet"%>
<h1>User List</h1>
<table border=1>
    <tr>
        <td>ID</td><td>Username</td><td>password</td><td>Email</td><td>Gender</td><td>Birthday</td>
    </tr>
    <%
        ResultSet rs= (ResultSet) request.getAttribute("name");
        if(rs==null){


    %>
    <tr>
        <td>No data!!!</td>
    </tr>
    <%}else{
          while(rs.next()){
    //get one by one
            out.println("<tr>");
            out.println("<td>"+rs.getInt("id")+"</td>");
            out.println("<td>"+rs.getString("username")+"</td>");
            out.println("<td>"+rs.getString("password")+"</td>");
            out.println("<td>"+rs.getString("email")+"</td>");
            out.println("<td>"+rs.getString("gender")+"</td>");
            out.println("<td>"+rs.getString("birthdate")+"</td>");

            out.println("</tr>");

         }
        }
          //we will get data in next demo - 6.LiveDemo #3
    %>
</table>
<%@include file="footer.jsp"%>