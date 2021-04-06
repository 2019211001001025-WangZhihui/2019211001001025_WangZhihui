package com.wangzhihui.week5.demo;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    public Connection dbConn;
    public void init() {
        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//<param-name>driver</param-name>
        String url="jdbc:sqlserver://localhost;databaseName=userdb";
        String username="sa";
        String password="wangzhihui0622";
        try { Class.forName(driver);
            dbConn = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password= request.getParameter("password");
        PrintWriter writer = response.getWriter();
        init();
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
        }}}
