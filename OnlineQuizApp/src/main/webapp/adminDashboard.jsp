<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="dao.QuizDAO"%>
<%@page import="model.Quiz"%>
<%@page import="java.util.List"%>
<%
    HttpSession sess = request.getSession(false);
    if (sess == null || sess.getAttribute("adminUsername") == null) {
        response.sendRedirect("adminLogin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Online Quiz App</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header>
        <h1>Admin Dashboard</h1>
        <nav>
            <ul>
                <li><a href="adminLogout">Admin Logout</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <div class="container">
            <h2>Manage Quizzes</h2>
            <%
                QuizDAO dao = new QuizDAO();
                List<Quiz> quizList = dao.getAllQuizzes();
                if (quizList != null && !quizList.isEmpty()) {
            %>
                <ul class="quiz-list">
                    <% for (Quiz quiz : quizList) { %>
                        <li>
                            <h3><%= quiz.getQuizTitle() %></h3>
                            <a href="deleteQuizServlet?quizId=<%= quiz.getQuizId() %>">Delete</a> or
                            <a href="viewResults.jsp?quizId=<%= quiz.getQuizId() %>&quizTitle=<%= quiz.getQuizTitle() %>">View Results</a>
                        </li><br>
                    <% } %>
                </ul>
            <% } else { %>
                <p>No quizzes available at the moment.</p>
            <% } %>
            <form action="addQuiz.jsp" method="post" >
                <input type="submit" value="Add New Quiz" class="option" >
            </form>
        </div>
    </main>
    <footer>
        <p>&copy; 2025 Online Quiz App</p>
    </footer>
</body>
</html>
