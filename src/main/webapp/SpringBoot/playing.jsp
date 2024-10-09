<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.demo.GameState" %>
<%--
  Created by IntelliJ IDEA.
  User: malte.aschenbach
  Date: 09.10.2024
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Playing</title>
</head>
<body>
<h1> Guessing Game (SpringBoot) </h1>
<p>Your last guess was ${ model.getLastGuess() }</p>
<p>
    <c:choose>
        <c:when test="${
            gameState == GameState.TOO_LOW
        }">
            That guess was to low.
        </c:when>
        <c:when test="${
            gameState == GameState.TOO_HIGH
        }">
            That guess was to high.
        </c:when>
        <c:when test="${
            gameState == GameState.SOLVED
        }">
            You were correct!
        </c:when>
    </c:choose>
</p>
<form action="/" method="post">
    <label for="guess">Your Guess:</label>
    <input type="number" name="guess">
    <input type="submit">
</form>
<p>You habe guessed ${ model.getNumberOfGuesses() } times.</p>
</body>
</html>
