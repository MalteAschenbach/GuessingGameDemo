package com.example.demo;

import java.io.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() { }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        session.setAttribute("correct",(int) (Math.random() * 100.0));
        session.setAttribute("guess",-1);

        RequestDispatcher dispatcher = request.getRequestDispatcher("test.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        HttpSession session = request.getSession();

        try {
            String guess_string = request.getParameter("guess");
            session.setAttribute("guess", Integer.parseInt(guess_string));
        } catch (NumberFormatException e) {
            System.out.println("Guess has to be an integer");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("test.jsp");
        dispatcher.forward(request, response);
    }


    public void destroy() {
    }
}