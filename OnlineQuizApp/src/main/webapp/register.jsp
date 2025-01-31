<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header>
        <h1>Register</h1>
      
    </header>

    <main>
        <div class="container">
        
			<h4 > 
			 	<%
    				String data= (String) request.getAttribute("msg");
    				if(data!=null)
    					out.println(data);
    			%>
    		</h4>        
            <h2>Create a New Account</h2>

            <!-- Registration Form -->
            <form action="registerServlet" method="post">
               <div>
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required><br>
               </div>

                <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required><br>
                
                </div>
               
               <div>
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" required><br>
               
               </div>
               
               <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required><br><br>
               </div>


                <input type="submit" value="Register">
            </form>

            <p>Already have an account? <a href="userLogin.jsp">Login here</a></p>
        </div>
    </main>

    <footer>
        <p>&copy; 2025 Online Quiz App</p>
    </footer>
</body>
</html>
