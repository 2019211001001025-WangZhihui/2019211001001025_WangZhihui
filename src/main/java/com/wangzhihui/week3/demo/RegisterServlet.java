package com.wangzhihui.week3.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//automatic -new --> servlet
@WebServlet(urlPatterns = {"/register"},loadOnStartup = 1)
public class RegisterServlet extends HttpServlet {
    Connection con = null;
    @Override
    public void init() throws ServletException{
        super.init();
                   /*ServletContext context=getServletContext();
                String driver=context.getInitParameter("driver");
                String url=context.getInitParameter("url");
                String username=context.getInitParameter("username");
                String password=context.getInitParameter("password");
                try{
                    Class.forName(driver);
                    con = DriverManager.getConnection(url, username, password);
                    System.out.println("init()-->"+ con);
                }catch (ClassNotFoundException | SQLException e){
                    e.printStackTrace();
                }
                   */
        con= (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//request come here- <from method=post>
        //get parameter from request .
        PrintWriter writer = response.getWriter();
        String username =request.getParameter("username");//name of input type-<input type="text" name="username">
        String password =request.getParameter("password");//<input type="password" name="password">
        String email =request.getParameter("email");//<input type="text" name="email">
        String gender =request.getParameter("gender");//<input type="radio" name="gender">
        String birthDate =request.getParameter("birthDate");//<input type="text name=" name="birthDate">

        try{
            Statement st=con.createStatement();
            String sql="insert into usertable(username,password,email,gender,birthdate)" +
                    "values('"+username+"','"+password+"','"+email+"'.'"+gender+"','"+birthDate+"')";
            System.out.println("sql"+sql);

            int n=st.executeUpdate(sql);
            System.out.println("n-->"+n);
            //sql="select username,password,email,gender,birthdate from usertable";
            //ResultSet rs=st.executeQuery(sql);
            //PrintWriter out=response.getWriter();
            //out.println("<html><title></title><body><table border=1><tr>");
            //out.println("<td>Username</td><td>password</td><td>Email</td><td>Gender</td><td>Birthday</td><tr/>");
            /*while(rs.next()){
                out.println("<tr>");
                out.println("<td>"+rs.getString("username")+"</td>");
                out.println("<td>"+rs.getString("password")+"</td>");
                out.println("<td>"+rs.getString("email")+"</td>");
                out.println("<td>"+rs.getString("gender")+"</td>");
                out.println("<td>"+rs.getString("birthdate")+"</td>");

                out.println("</tr>");
            }*/
            //out.println("</table></body></html>");
            //use request attribute
            //set rs into request attribute
            //request.setAttribute("rsname",rs);

            //request.getRequestDispatcher("userList.jsp").forward(request,response);
            //no more here
            //System.out.println("i am in RegisterServlet-->doPost()--> after forward()");


            response.sendRedirect("login");
        }catch(SQLException e){
            e.printStackTrace();
        }
        //print - write into response

        //writer.println("<br> username :"+username);
        //writer.println("<br> password :"+password);
        //writer.println("<br> email :"+email);
        //writer.println("<br> gender :"+gender);
        //writer.println("<br> birthDate :"+birthDate);
        //writer.close();

    }
}
