<%@page import="java.sql.*"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.QuestionDAO"%>
<%@page import="model.Question"%>
<%@page import="java.util.List"%>

<%
    HttpSession sess = request.getSession(false);
    if (sess == null || sess.getAttribute("username") == null) {
        response.sendRedirect("userLogin.jsp");
        return;
    }
    Map<Integer, Integer> submittedAnswers=(Map<Integer , Integer >)request.getAttribute("map");
    
    int totalQuestions=(int)request.getAttribute("totalQuestions");
    int correctAnswers=(int)request.getAttribute("correctAnswers");
    double scorePercentage=(double)request.getAttribute("scorePercentage");
    List<Question> questions=(List<Question>)request.getAttribute("questions");
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Results</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header>
        <h1>Quiz Platform</h1>
        <p>Your quiz results are here!</p>
    </header>

    <main>
        <h2>Quiz Results</h2>
        <p>Total Questions: <%= totalQuestions %></p>
        <p>Correct Answers: <%= correctAnswers %></p>
        <p>Your Score: <%= Math.round(scorePercentage) %>%</p>

        <h3>Review Your Answers</h3>
<div class="review-container">
    <% for (Question question : questions) { %>
    <div class="quiz-question">
        <p class="question-text"><%= question.getQuestionTitle() %></p>
        <ul>        
            <li class="<%= (submittedAnswers.get(question.getQuestionId()) != null && submittedAnswers.get(question.getQuestionId()) == 1) ? "highlight" : "" %>">
                <%= question.getOption1() %>
                <% if (question.getCorrectAnswer() == 1) { %> <span class="correct">(Correct Answer)</span> <% } %>
            </li>
            <li class="<%= (submittedAnswers.get(question.getQuestionId()) != null && submittedAnswers.get(question.getQuestionId()) == 2) ? "highlight" : "" %>">
                <%= question.getOption2() %>
                <% if (question.getCorrectAnswer() == 2) { %> <span class="correct">(Correct Answer)</span> <% } %>
            </li>
            <li class="<%= (submittedAnswers.get(question.getQuestionId()) != null && submittedAnswers.get(question.getQuestionId()) == 3) ? "highlight" : "" %>">
                <%= question.getOption3() %>
                <% if (question.getCorrectAnswer() == 3) { %> <span class="correct">(Correct Answer)</span> <% } %>
            </li>
            <li class="<%= (submittedAnswers.get(question.getQuestionId()) != null && submittedAnswers.get(question.getQuestionId()) == 4) ? "highlight" : "" %>">
                <%= question.getOption4() %>
                <% if (question.getCorrectAnswer() == 4) { %> <span class="correct">(Correct Answer)</span> <% } %>
            </li>
        </ul>
    </div>
    <% } %>
</div>


        <div class="submit-container">
            <a href="quizList.jsp" class="bton">Take Another Quiz</a>
            <a href="userLogout" class="bton">Logout</a>
        </div>
    </main>

    <footer>
        <p>&copy; 2025 Quiz Platform. All rights reserved.</p>
    </footer>
</body>
</html>
