<%@page import="model.Quiz"%>
<%@page import="dao.QuestionDAO"%>
<%@page import="model.Question"%>
<%@page import="java.util.List"%>
<%
    // Retrieve quiz ID from the URL
    int quizId = Integer.parseInt(request.getParameter("quizId"));
	String username=(String)session.getAttribute("username")	;

    HttpSession sess = request.getSession(false);
    if (sess == null || username == null) {
        response.sendRedirect("userLogin.jsp");
        return;
    }

    // Validate that quizId is valid
    if (quizId == -1) {
        out.println("<p>Error: Quiz ID is missing. Please go back and select a quiz.</p>");
        return;
    }

    // Retrieve the quiz title (replace with actual database query if available)
    String quizTitle =(String)request.getParameter("quizTitle"); 
    List<Question> questions = new QuestionDAO().getQuestionsForQuiz(quizId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Platform</title>
    <link rel="stylesheet" href="css/style.css">
<body>
    <header>
        <h1>Quiz Platform</h1>
        <p>Test your knowledge and learn something new!</p>
    </header>

    <main>
        <h2>Quiz: <%= quizTitle %></h2>

        <form action="result" method="post" class="quiz-form">
            <!-- Include quiz ID as a hidden field -->
            <input type="hidden" name="quizId" value="<%= quizId %>">
            <input type="hidden" name="username" value="<%= username %>">

            <% 
                // Initialize question number counter
                int questionNumber = 1;

                // Loop through the questions list
                for (int i = 0; i < questions.size(); i++) { 
                    Question question = questions.get(i);
            %>
            <div class="quiz-question">
                <!-- Question Text -->
                <p class="question-text"><%= questionNumber %>. <%= question.getQuestionTitle() %></p>

                <!-- Answer Options -->
                <div class="options">
                    <label class="optional">
                        <input type="radio" name="question_<%= question.getQuestionId() %>" value="1"> <%= question.getOption1() %> 
                    </label><br>
                    <label class="optional">
                        <input type="radio" name="question_<%= question.getQuestionId() %>" value="2"> <%= question.getOption2() %>
                    </label><br>
                    <label class="optional">
                        <input type="radio" name="question_<%= question.getQuestionId() %>" value="3"> <%= question.getOption3() %>
                    </label><br>
                    <label class="optional">
                        <input type="radio" name="question_<%= question.getQuestionId() %>" value="4"> <%= question.getOption4() %>
                    </label>
                </div>
            </div>
            
            <!-- Hidden field to track unanswered questions -->
    		<input type="hidden" name="question_<%= question.getQuestionId() %>" value="">
    
            <% 
                    questionNumber++;
                } 
            %>

            <!-- Submit Button -->
            <div class="submit-container">
                <button type="submit" class="btn">Submit </button>
            </div>
        </form>
    </main>

    <footer>
        <p>&copy; 2025 Quiz Platform. All rights reserved.</p>
    </footer>
</body>
</html>
