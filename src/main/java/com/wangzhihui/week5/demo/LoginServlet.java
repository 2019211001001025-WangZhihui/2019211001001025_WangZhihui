package com.wangzhihui.week5.demo;

import com.wangzhihui.Dao.UserDao;
import com.wangzhihui.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

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

        //when user click login menu- method is get

        try {
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        // TODO 3:GET REQUEST PARAMETER - USERNAME AND PASSWORD
        String username = request.getParameter("username");
        String password= request.getParameter("password");


        //now move jdbc code in dao - MVC design
        //write mvc code
        //user model and
        try {
        UserDao userDao=new UserDao();
            User user=userDao.findByUsernamePassword(con,username,password);//this methods use for login
            if (user!=null){
                //valid
                //week 8 code - demo #1- use cookie for session
                //create cookie
                //step 1: create an object of cookie class
                Cookie c=new Cookie("sessionid",""+user.getID());//sessionid = user- id
                //step 2: set age of cookie
                c.setMaxAge(10*60);//in sec- 10 min - 7 days - 60*60*24*7
                //step 3: add cookie into response
                response.addCookie(c);

                //add code for remember me
                String rememberMe=request.getParameter("rememberMe");//1=checked, null if checked
                if(rememberMe!=null && rememberMe.equals("1")){
                    //want to remember me
                    //create 3 cookies
                    Cookie usernameCookies=new Cookie("cUsername",user.getUsername());
                    Cookie passwordCookies=new Cookie("cPassword",user.getPassword());
                    Cookie rememberMeCookies=new Cookie("cRememberMe",rememberMe);

                    //set age of cookies
                    usernameCookies.setMaxAge(5);//5 sec- test --- 15 days = 60*60*24*15
                    passwordCookies.setMaxAge(5);
                    rememberMeCookies.setMaxAge(5);
                    //add 3 cookies into response
                    response.addCookie(usernameCookies);
                    response.addCookie(passwordCookies);
                    response.addCookie(rememberMeCookies);

                }



                //create a session
                HttpSession session=request.getSession();//create a new session if session doesnot exist - otherwise return existing session
                //check session id
                System.out.println("session id-->"+session.getId());//session id
                //set time for session
                session.setMaxInactiveInterval(10);//for 5 10 section if request not come in - tomcat kill session -set 60*60==1 h


                //set user into request
                //week 8 0- change request(one page) to session - so we can get session attribute in many jsp page - login.jsp and header.jsp

                session.setAttribute("user",user);//get user info in session
                try {
                    request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            }else {
                //invalid
                request.setAttribute("massage","Username or Password Error!!!");
                try {
                    request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            }
            //forward - JSP
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }








        //TODO 4: VALIDATE USER -SELECT * FROM USERTABLE WHERE USERNAME='XXX'
        // AND PASSWORD='YYY'
        /*String sql="select username,password from usertable where username='"+username+"' and password='"+password+"'";
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
        }*/

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

