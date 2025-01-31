<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login - Online Quiz App</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Link to CSS file -->
</head>
<body>
    <header>
        <h1>User Login</h1>
    </header>

    <main>
    
    <%
    	String data =(String)request.getAttribute("msg");
    	if(data!=null)
    		out.println("<center><h4>"+data+"</h4></center>");
    %>
        <div class="container">
            <h2>Please enter your login details</h2>

            <!-- Login Form -->
            <form action="userLoginServlet" method="post">
             <% String errorMessage = (String) request.getAttribute("errorMessage");
               if (errorMessage != null) { %>
                <p style="color:red;"><%= errorMessage %></p>
            <% } %>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <button type="submit" class="btn">Login</button>
                
               <div class="form-group"> <a href="register.jsp">New User???</a> </div>
            </form>

        </div>
    </main>

    <footer>
        <p>&copy; 2025 Online Quiz App</p>
    </footer>
</body>
</html>
