<%-- 
    Document   : index
    Created on : 5 Oct, 2016, 5:40:26 PM
    Author     : MANOSIJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Please enter the details</h1>
        <form action="login">
            Name      : <input type="text" name="username"><br />
            Password  : <input type="text" name="passwords"><br />
                <input type="submit" value="submit">
        </form>
        <p class="message">Forgot Password? <a href="#">Click Here</a></p>
    </body>
</html>
