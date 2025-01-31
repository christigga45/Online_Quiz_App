<%@page import="dao.QuizDAO"%>
<%@page import="model.Quiz"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    HttpSession sess = request.getSession(false);
    if (sess == null || sess.getAttribute("username") == null) {
        response.sendRedirect("userLogin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz List - Online Quiz App</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Link to CSS file -->
</head>
<body>
    <header>
        <h1>Available Quizzes</h1>
        <nav>
            <ul>
                <li><a href="userLogout">Logout</a></li>
            </ul>
    </header>
    <main>
        <div class="container">
            <h2>List of Quizzes</h2>

            <!-- Check if there are quizzes -->
             <%
            	QuizDAO dao=new QuizDAO();
            	List<Quiz> quizList =dao.getAllQuizzes();
                if (quizList != null && !quizList.isEmpty()) {
            %>
                <ol class="quiz-list">
                    <% 
                        // Display quizzes
                        for (Quiz quiz : quizList) {
                    %>
                        <li>
                            <a href="quiz.jsp?quizId=<%= quiz.getQuizId() %>&quizTitle=<%=quiz.getQuizTitle()%>">
                                <%= quiz.getQuizTitle() %>
                            </a> 
                        </li>
                    <% 
                        } 
                    %>
                </ol>
            <% 
                } else {
            %>
                <p>No quizzes available at the moment.</p>
            <% 
                }
            %>

        </div>
    </main>

    <footer>
        <p>&copy; 2025 Online Quiz App</p>
    </footer>
</body>
</html>
