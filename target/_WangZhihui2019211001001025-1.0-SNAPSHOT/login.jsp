<%--
  Created by IntelliJ IDEA.
  User: 79805
  Date: 2021/4/6
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<form method="post" action="login">
    <%= "Login" %><br/>
    Username<input type="text" name="username"><br/>
    Password<input type="password" name="password"><br/>
    <input type="submit" value="login"/>

</form>
<%@include file="footer.jsp"%>
