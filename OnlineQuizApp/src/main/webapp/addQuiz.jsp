<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Quiz</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Link to your CSS file -->
    <script>
        // JavaScript code to dynamically add new questions and options
        let questionCount = 1;

        function addQuestion() {
            const container = document.getElementById('question-container');
            const questionHTML = `
                <div class="question-section">
                    <h3>Question ${questionCount}</h3>
                    <label>Question Text:</label>
                    <input type="text" name="questionTitle${questionCount}" required><br>

                    <label>Option 1:</label>
                    <input type="text" name="option1${questionCount}" required><br>
                    <label>Option 2:</label>
                    <input type="text" name="option2${questionCount}" required><br>
                    <label>Option 3:</label>
                    <input type="text" name="option3${questionCount}" required><br>
                    <label>Option 4:</label>
                    <input type="text" name="option4${questionCount}" required><br>

                    <label>Correct Answer (Option Number):</label>
                    <input type="number" name="correctAnswer${questionCount}" min="1" max="4" required><br><br>
                </div>`;
            container.insertAdjacentHTML('beforeend', questionHTML);
            questionCount++;
        }
    </script>
</head>
<body>
    <header>
        <h1>Admin Dashboard</h1>
        <nav>
            <ul>
                <li><a href="index.html">Admin Logout</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <div class="container">
            <h2>Add a New Quiz</h2>

            <!-- Form to add a new quiz with questions -->
            <form action="addQuizServlet" method="post">
                <label for="quizTitle">Quiz Title:</label>
                <input type="text" name="quizTitle" required ><br><br>

                <!-- Dynamic question sections will be appended here -->
                <div id="question-container"></div>

                <button type="button" onclick="addQuestion()" class="option">Add Question</button><br><br>

                <input type="submit" value="Add Quiz" class="option">
            </form>

            <!-- Optional: Show message if quiz is added successfully or failed -->
            <% 
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
                <p><%= message %></p>
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
