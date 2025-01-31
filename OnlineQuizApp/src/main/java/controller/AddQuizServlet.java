package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuizDAO;
import model.Question; // Import the Question class

@WebServlet("/addQuizServlet")
public class AddQuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the quiz title from the request parameter
        String quizTitle = request.getParameter("quizTitle");

        // Create an instance of the QuizDAO to interact with the database
        QuizDAO quizDAO = new QuizDAO();

        // Add the quiz title to the database
        boolean isAdded = quizDAO.addQuiz(quizTitle);
        
        if (isAdded) {
            // Fetch the quizId after adding the quiz
            int quizId = quizDAO.getQuizId(quizTitle);
            
            if (quizId != -1) {
                // Get the parameters for questions and options from the form
                String[] questionTitles = request.getParameterValues("questionTitle");
                String[] options1 = request.getParameterValues("option1");
                String[] options2 = request.getParameterValues("option2");
                String[] options3 = request.getParameterValues("option3");
                String[] options4 = request.getParameterValues("option4");
                String[] correctAnswers = request.getParameterValues("correctAnswer");
                
                if (questionTitles != null && questionTitles.length > 0) {
                    // Iterate through each question and add it to the database
                    for (int i = 0; i < questionTitles.length; i++) {
                        String questionTitle = questionTitles[i];
                        String option1 = options1[i];
                        String option2 = options2[i];
                        String option3 = options3[i];
                        String option4 = options4[i];
                        int correctAnswer = Integer.parseInt(correctAnswers[i]);

                        // Create a new Question object with the form data
                        Question question = new Question(0, quizId, questionTitle, option1, option2, option3, option4, correctAnswer);

                        // Add the question to the quiz using quizId
                        boolean isQuestionAdded = quizDAO.addQuestionToQuiz(quizId, question);

                        if (!isQuestionAdded) {
                            // If adding the question fails, return an error message and stop
                            request.setAttribute("message", "Failed to add question " + (i + 1));
                            request.getRequestDispatcher("addQuiz.jsp").forward(request, response);
                            return;
                        }
                    }

                    ServletContext context=request.getServletContext();
                    context.setAttribute("quizTitle", quizTitle);
                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    // If no questions were provided
                    request.setAttribute("message", "No questions provided.");
                    request.getRequestDispatcher("addQuiz.jsp").forward(request, response);
                }
            } else {
                // Handle invalid quizId
                request.setAttribute("message", "Failed to retrieve valid quiz ID.");
                request.getRequestDispatcher("addQuiz.jsp").forward(request, response);
            }
        } else {
            // If quiz addition fails
            request.setAttribute("message", "Failed to add quiz.");
            request.getRequestDispatcher("addQuiz.jsp").forward(request, response);
        }
    }
}
