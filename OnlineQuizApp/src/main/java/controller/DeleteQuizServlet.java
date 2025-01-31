package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuizDAO;

@WebServlet("/deleteQuizServlet")
public class DeleteQuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the quizId from the request parameter
        String quizIdStr = request.getParameter("quizId");
        System.out.println(quizIdStr);
        if (quizIdStr != null) {
            int quizId = Integer.parseInt(quizIdStr);

            // Call the deleteQuiz method in QuizDAO to delete the quiz
            QuizDAO dao = new QuizDAO();
            boolean success = dao.deleteQuiz(quizId);

            if (success) {
                // If the deletion is successful, redirect to the quiz list page
            	request.setAttribute("msg", "Quiz Deleted Successfully !");
                RequestDispatcher rd=request.getRequestDispatcher("adminDashboard.jsp");
                rd.forward(request, response);
            } else {
                // If the deletion fails, redirect to an error page or show an error message
                request.setAttribute("errorMessage", "Failed to delete quiz. Please try again.");
                request.getRequestDispatcher("quizList.jsp").forward(request, response);
            }
        } else {
            // Redirect to quiz list page if quizId is missing
            response.sendRedirect("quizList.jsp");
        }
    }
}
