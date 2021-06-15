package com.lab3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HitCountServlet", value = "/HitCountServlet")
public class HitCountServlet extends HttpServlet {
    int hitCount = 0;
    public void init() throws ServletException{
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        hitCount++;
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<center><h1> Total Number of Hits </h1>");
        out.println("<h1>"+hitCount+"</h1></center>");
        out.println("</html></body>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
