package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;
import model.Admin;

@WebServlet("/adminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Logic to validate admin credentials
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.validateAdmin(username, password);

        if (admin!=null) {
            // Redirect to admin dashboard or quiz management page
        	HttpSession session = request.getSession();
            session.setAttribute("adminUsername", username);
            response.sendRedirect("adminDashboard.jsp");
        } else {
            // Set error message and forward back to login page
            request.setAttribute("errorMessage", "Invalid admin username or password.");
            request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
        }
    }
}
