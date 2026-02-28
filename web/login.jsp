<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-color: #68c9cc;
            }
        </style>
        <div class="section-one">
            <div class="login-box">
                <h2 class="title">Workshop 1</h2>
                <form action="DispatchServlet" method="POST">
                    <div class="form-group">
                        <label for="email">Username:</label>
                        <input type="text" class="form-control" name="txtUsername">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" name="txtPassword">
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" name="txtRemember">Remember me</label>
                    </div>
                    <div style="color:#ff3333; font-weight: bold; font-size: 12px;">
                       ${requestScope.NOTIFICATION} 
                    </div>
                    
                    <button type="submit" class="btn btn-success" 
                            name="btnAction" value="Login">Login</button>
                </form>
                <h4 class="question">Haven't got an account?</h4>
                <a href="register.jsp">
                    Register here!
                </a>
            </div>
        </div>
    </body>
</html>
