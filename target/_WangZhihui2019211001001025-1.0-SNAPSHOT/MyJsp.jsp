<%--
  Created by IntelliJ IDEA.
  User: 79805
  Date: 2021/3/16
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<a href="index.jsp">go to ecjtu</a><!-- method is GET-->
<form method="post"><!--what is method when we use form?--><!-- its GET , why? default is GET , form data in not added in the URL , u can see-->
    <!-- its better to use POST in form , data in not added in the URL-->
    Name:<input type="text" name="name"><br/>
    ID:<input type="text" name="id"><br/>
    <input type="submit" value="Send data to server"/>
</form>
<%@include file="footer.jsp"%>
