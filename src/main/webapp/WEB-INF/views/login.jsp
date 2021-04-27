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
<%
    //read cookies
    Cookie[] allCookies=request.getCookies();//give all cookies
    String username="", password="", rememberMeVal="";
    if(allCookies!=null){
        //we read 3 cookies
        for(Cookie c:allCookies){
            //get one by one
            if(c.getName().equals("cUsername")){
                //get value of this cookies
                username=c.getValue();
            }
            if(c.getName().equals("cPassword")){
                //get value of this cookies
                password=c.getValue();
            }
            if(c.getName().equals("cRememberMe")){
                //get value of this cookies
                rememberMeVal=c.getValue();
            }

        }
    }
%>
<form method="post" action="login">
    <%= "Login" %><br/>
    Username<input type="text" name="username" value="<%=username%>"><br/>
    Password<input type="password" name="password" value="<%=password%>"><br/>
<input type="checkbox" name="rememberMe" value="1" <%=rememberMeVal.equals("1") ?"checked":""%>checked/>RememberMe<br/>
    <input type="submit" value="Submit"/>

</form>
<%@include file="footer.jsp"%>
