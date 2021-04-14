package com.wangzhihui.week5.demo;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() throws ServletException {
        super.init();
        /// TODO 1: GET 4 CONTEXT PARAM - DRIVER , URL , USERNAME , PASSWORD
        // TODO 2: GET JDBC connection
        con = (Connection) getServletContext().getAttribute("con");
        //String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//<param-name>driver</param-name>
        //String url="jdbc:sqlserver://localhost;databaseName=userdb";
        //String username="sa";
        //String password="wangzhihui0622";
        //try { Class.forName(driver);
            //dbConn = DriverManager.getConnection(url,username,password);
        //} catch (ClassNotFoundException | SQLException e) {
           // e.printStackTrace();
        //}
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        // TODO 3:GET REQUEST PARAMETER - USERNAME AND PASSWORD
        String username = request.getParameter("username");
        String password= request.getParameter("password");
        //TODO 4: VALIDATE USER -SELECT * FROM USERTABLE WHERE USERNAME='XXX'
        // AND PASSWORD='YYY'
        String sql="select username,password from usertable where username='"+username+"' and password='"+password+"'";
        try{
            ResultSet rs= con.createStatement().executeQuery(sql);
            if(rs.next()){
                //out.print("Login Successful!!!");
                //out.print("Welcome"+username);
                request.setAttribute("id",rs.getInt("id"));
                request.setAttribute("username",rs.getString("username"));
                request.setAttribute("password",rs.getString("password"));
                request.setAttribute("email",rs.getString("email"));
                request.setAttribute("gender",rs.getString("gender"));
                request.setAttribute("birthDate",rs.getString("birthdate"));
                //forward to user info jsp
                try {

                    request.getRequestDispatcher("userInfo.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            }else{
                //out.print("Username or password Error!!!");
                request.setAttribute("massage","Username or Password Error!!!");
                try {
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            }
        }catch(SQLException throwables){
                    throwables.printStackTrace();
        }

        /*init();
        try {
            if( dbConn != null){


                String sql = "SELECT * FROM usertable WHERE username=? AND password=?;";
                PreparedStatement ps = dbConn.prepareStatement(sql);
                ps.setString(1,username);
                ps.setString(2,password);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    writer.println("Login Success!!!");
                    writer.println("Welcome "+username+".");
                }else{
                    writer.print("Username or Password Error!!!");
                }
            }
        }catch (Exception e) {
            System.out.println(e);
        }}
        */}}

