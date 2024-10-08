<%@ page import="com.example.demo.GameState" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.example.demo.Model" %><%--
  Created by IntelliJ IDEA.
  User: malte.aschenbach
  Date: 07.10.2024
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> Guessing Game (JSP) </h1>
<%
    Model model = (Model) session.getAttribute("model");
    GameState gameState = model.getGameState();
    Integer guess = model.getLastGuess();
    int numberOfGuesses = model.getNumberOfGuesses();
%>

<p>Currently Playing: <%
    out.println(session.getServletContext().getAttribute("activeSessions"));
%></p>

<%
    if (gameState == GameState.START) {
%>
<p>Welcome to Maltes Guessing Game!</p>
<p>Wowowowoow</p>
<%
} else if (gameState == GameState.TOO_LOW
        || gameState == GameState.TOO_HIGH
        || gameState == GameState.SOLVED
) {
%>
<p>
    Your last guess was:
    <%
        out.println(guess);
    %>
</p>
<p>
    <%
        switch (gameState) {
            case TOO_LOW:
                out.println("That guess was too low");
                break;
            case TOO_HIGH:
                out.println("That guess was too high");
                break;
            case SOLVED:
                out.println("That's correct!");
                break;
        }
    %>
</p>
<%
} else if (gameState == GameState.INVALID_INPUT) {
%>
<p>Guess has to be an integer</p>
<%
    }
%>

<% if (gameState != GameState.SOLVED) { %>
<form action="/demo/hello-servlet" method="post">
    <label for="guess">Your Guess:</label>
    <input type="number" name="guess">
    <input type="submit">
</form>
<% } %>

<p>You needed <% out.println(numberOfGuesses); %> guesses</p>
</body>
</html>
