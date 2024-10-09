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
    <title>Starting...</title>
</head>
<body>
<h1> Guessing Game (SpringBoot) </h1>
<p>Currently playing: ${activeSessions}</p>
<form action="/" method="post">
    <label for="guess">Your Guess:</label>
    <input type="number" name="guess">
    <input type="submit">
</form>
</body>
</html>
