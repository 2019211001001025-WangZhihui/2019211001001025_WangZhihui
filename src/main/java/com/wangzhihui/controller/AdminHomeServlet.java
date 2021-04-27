package com.wangzhihui.controller;

import com.wangzhihui.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminHomeServlet", value = "/admin/home")
public class AdminHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO 1: get username from session
        HttpSession session=request.getSession(false);//return session or NULL (no session) but its not create a new session
        if(session!=null&& session.getAttribute("user")!=null){
            //TODO 2: if username == admin - forward to /WEB-INF/views/admin/index.jsp
            User user=(User) session.getAttribute("user");
            if("admin".equals(user.getUsername())){//admin username must be in table
                request.getRequestDispatcher("../WEB-INF/views/admin/index.jsp").forward(request,response);
            }else{
                //have session but its not admin user
                session.invalidate();//kill session right now
                request.setAttribute("message","Unauthorized Access Admin Module!!!");
                request.getRequestDispatcher("../WEB-INF/views/login.jsp").forward(request,response);
            }
        }else{
            //TODO 3: else ask for login
            //no session - then user click the link
            request.setAttribute("message","Please Login as admin!!!");
            request.getRequestDispatcher("../WEB-INF/views/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
