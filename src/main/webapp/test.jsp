<%--
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
    int guess = (int) session.getAttribute("guess");
    int correct = (int) session.getAttribute("correct");
%>


<%
    if (guess != -1) {
%>
<p>
    Your last guess was:
    <%
        out.println(guess);
    %>
</p>
<p>
    <% if (guess < correct) { %>
        That guess was too low
    <% } else if (guess > correct) { %>
        That guess was too high
    <% } else { %>
        That's correct!
    <% } %>
</p>
<%
    } else {
%>
    <p>Guess has to be an integer</p>
<%
    }
%>

<form action="/demo/hello-servlet" method="post">
    <label for="guess">Your Guess:</label>
    <input type="number" name="guess">
    <input type="submit">
</form>

</body>
</html>
