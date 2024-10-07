package com.example.demo;

import java.io.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() { }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        session.setAttribute("correct",(int) (Math.random() * 100.0));
        session.setAttribute("guess",-1);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> Guessing Game </h1>");
        out.println("<form action=\"/demo/hello-servlet\" method=\"post\"> <label for=\"guess\">Your Guess:</label> <input type=\"number\" name=\"guess\"> <input type=\"submit\"></form>");
        out.println("</body></html>");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> Guessing Game </h1>");
        try {
            String guess_string = request.getParameter("guess");
            session.setAttribute("guess", Integer.parseInt(guess_string));
        } catch (NumberFormatException e) {
            out.println("Guess has to be an integer");
        }
        int guess = (int) session.getAttribute("guess");
        int correct = (int) session.getAttribute("correct");

        if(guess != -1 || true) {
            out.println("Your last guess was: " + guess + "<br>");
            if (guess < correct) {
                out.println("That guess was too low");
            } else if (guess > correct) {
                out.println("That guess was too high");
            } else {
                out.println("That's correct!");
            }
        }
        out.println("<form action=\"/demo/hello-servlet\" method=\"post\"> <label for=\"guess\">Your Guess:</label> <input type=\"number\" name=\"guess\"> <input type=\"submit\"></form>");
        out.println("</body></html>");
    }


    public void destroy() {
    }
}