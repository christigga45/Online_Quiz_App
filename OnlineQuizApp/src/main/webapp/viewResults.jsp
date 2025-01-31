<%@page import="dao.ResultsDAO"%>
<%@page import="model.Result"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Quiz Results</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
     <nav>
            <ul>
                <li><a href="adminLogout">Admin Logout</a></li>
                <li><a href="addQuiz.jsp">Add New Quiz</a></li>
            </ul>
        </nav>
    </header>
    <% String quizTitle = (String) request.getParameter("quizTitle");
    	HttpSession sess = request.getSession(false);
   		 if (sess == null || sess.getAttribute("adminUsername") == null) {
       		 response.sendRedirect("adminLogin.jsp");
        return;
    	}
    %>
    <h1><%= quizTitle %> Quiz Results</h1>
    
    <table>
        <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Score</th>
                <th>Percentage</th>
            </tr>
        </thead>
        <tbody>
            <%
                ResultsDAO dao = new ResultsDAO();
                List<Result> resultList = dao.getAllResults(quizTitle);

                if (resultList != null && !resultList.isEmpty()) {
                    int rank = 1; // Initialize rank counter
                    for (Result result : resultList) {
            %>
            <tr>
                <td><%= rank++ %></td>
                <td><%= result.getUsername() %></td>
                <td><%= result.getScore() %></td>
                <td><%= result.getPercentage() %> %</td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="4">No results available.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    
    
</body>
</html>
