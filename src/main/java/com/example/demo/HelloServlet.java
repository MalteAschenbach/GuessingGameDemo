package com.example.demo;

import java.io.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private int correct;
    private int guess;

    public void init() {
        correct = (int) (Math.random() * 100.0);
        guess = -1;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> Guessing Game </h1>");
        if(guess != -1) {
            out.println("Your last guess was: " + guess);
            if (guess < correct) {
                out.println("That guess was too low");
            } else if (guess > correct) {
                out.println("That guess was too high");
            }
        }
        out.println("<form action=\"/demo/hello-servlet\" method=\"post\"> <label for=\"guess\">Your Guess:</label> <input type=\"number\" name=\"guess\"> <input type=\"submit\"></form>");
        out.println("</body></html>");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> Guessing Game </h1>");
        try {
            String guess_string = request.getParameter("guess").toString();
           guess = Integer.parseInt(guess_string);
        } catch (NumberFormatException e) {
            out.println("Guess has to be an integer");
        }
        if(guess != -1) {
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