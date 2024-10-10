<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form action="/playing" method="get">
    <label for="guess">Your Guess:</label>
    <input type="number" name="guess">
    <input type="submit">
</form>

<h2>Winners</h2>
<table>
    <tr>
        <th>Winner</th>
        <th>Number of guesses</th>
    </tr>
    <tbody>
    <c:forEach items="${winners}" var="winner">
        <tr>
            <td>
                <c:out value="${winner.name}"></c:out>
            </td>
            <td>
                <c:out value="${winner.numberOfGuesses}"></c:out>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
