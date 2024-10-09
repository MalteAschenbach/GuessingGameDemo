<%--
  Created by IntelliJ IDEA.
  User: malte.aschenbach
  Date: 09.10.2024
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Win</title>
</head>
<body>
    <h1> Guessing Game (SpringBoot) </h1>
    <h2>You won in ${model.getNumberOfGuesses()} guesses!</h2>
    <form action="/" method="dialog">
       <label for="name" >Your Name:</label>
        <input type="text" name="name">
        <input type="submit">
    </form>
</body>
</html>
