package com.wangzhihui.controller;

import com.wangzhihui.Dao.UserDao;
import com.wangzhihui.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "UpdateUserServlet", value = "/updateUser")//url
public class UpdateUserServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        con =(Connection)getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //TODO 1:forward to WEB-INF/views/updateUser.jsp
        request.getRequestDispatcher("/WEB-INF/views/updateUser.jsp");
        //TODO 2:create one jsp page - update User
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //TODO 1: get all (6) request parameters
        int id=Integer.valueOf(request.getParameter("id"));
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String gender=request.getParameter("gender");
        String birthDate=request.getParameter("birthDate");
        //TODO 2: create an object of User Model
        //TODO 3: set all 6 request parameters values into User model - setXXX()
        User user=new User();
        user.setID(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setGender(gender);
        user.setBirthdate(DateUtil.convertStringToUtil(birthDate));
        //TODO 4: create an object if UserDao
        UserDao userDao=new UserDao();
        //TODO 5: Call updateUser() inUserDao
        try{
            int n=userDao.updateUser(con,user);
            User updateUser=userDao.findById(con,id);
            HttpSession session=request.getSession();
            session.removeAttribute("user");
            session.setAttribute("user",updateUser);
        //TODO 6: forward to WEB-INF/views/userInfo.jsp
            request.getRequestDispatcher("accountDetails").forward(request,response);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private static class DateUtil {
        public static Date convertStringToUtil(String birthDate) {

            return null;
        }
    }
}
