package com.example.demo;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
@WebListener()
public class HelloServlet extends HttpServlet implements HttpSessionListener {

    public int n = 0;

    public void init() {
    }

    public void sessionCreated(HttpSessionEvent se) {
        Integer activeSessions = (Integer) se.getSession().getServletContext().getAttribute("activeSessions");
        if (activeSessions == null) {
            activeSessions = 0;
        }
        se.getSession().getServletContext().setAttribute("activeSessions", activeSessions + 1);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        n--;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        session.setAttribute("correct", (int) (Math.random() * 100.0));
        session.setAttribute("guess", -1);
        session.setAttribute("game_state", GameState.START);

        RequestDispatcher dispatcher = request.getRequestDispatcher("test.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        HttpSession session = request.getSession();

        try {
            String guess_string = request.getParameter("guess");
            session.setAttribute("guess", Integer.parseInt(guess_string));

            int guess = (int) session.getAttribute("guess");
            int correct = (int) session.getAttribute("correct");

            if (guess < correct) {
                session.setAttribute("game_state", GameState.TOO_LOW);
            } else if (guess > correct) {
                session.setAttribute("game_state", GameState.TOO_HIGH);
            } else {
                session.setAttribute("game_state", GameState.SOLVED);
            }
        } catch (NumberFormatException e) {
            session.setAttribute("game_state", GameState.INVALID_INPUT);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("test.jsp");
        dispatcher.forward(request, response);
    }


    public void destroy() {
    }
}