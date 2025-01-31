package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Logic to validate the user's credentials
        UserDAO userDAO = new UserDAO();
        User user = userDAO.userExists(username, password);

        if (user!=null) {
            // Redirect to the quiz page or dashboard
        	HttpSession session=request.getSession();
        	session.setAttribute("username", username);
            response.sendRedirect("quizList.jsp");
            
        } else {
            // Set error message and forward back to login page
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("userLogin.jsp").forward(request, response);
        }
    }
}
