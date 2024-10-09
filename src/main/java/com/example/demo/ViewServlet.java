package com.example.demo;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(value = "/guessing_game_servlet")
@WebListener()
public class ViewServlet extends HttpServlet implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        Integer activeSessions = (Integer) sessionEvent
            .getSession()
            .getServletContext()
            .getAttribute("activeSessions");

        if (activeSessions == null) {
            activeSessions = 0;
        }

        sessionEvent
            .getSession()
            .getServletContext()
            .setAttribute("activeSessions", activeSessions + 1);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        session.setAttribute("model", new Model());

        RequestDispatcher dispatcher = request.getRequestDispatcher("Servlet/start.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        HttpSession session = request.getSession();

        Model model = (Model) session.getAttribute("model");
        model.recomputeBasedOnInput(request.getParameter("guess"));
        session.setAttribute("model", model);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Servlet/start.jsp");
        dispatcher.forward(request, response);
    }
}