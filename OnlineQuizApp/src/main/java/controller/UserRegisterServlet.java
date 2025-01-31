package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

@WebServlet("/registerServlet")
public class UserRegisterServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		User user=new User();
		
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		user.setEmail(req.getParameter("email"));	
		user.setPassword(req.getParameter("fullName"));
		
		UserDAO dao=new UserDAO();
		
		if(dao.registerUser(user))
		{
			req.setAttribute("msg", "User Registered Successfully !!!");
			RequestDispatcher rd=req.getRequestDispatcher("userLogin.jsp");
			rd.forward(req, res);
		}
		else
		{
			req.setAttribute("msg", "User Registration Failed");
			RequestDispatcher rd=req.getRequestDispatcher("register.jsp");
			rd.forward(req, res);
		}
	}
}