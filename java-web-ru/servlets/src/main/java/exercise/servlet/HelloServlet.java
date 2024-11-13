package exercise.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // BEGIN
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var name = request.getParameter("name");
        var message = "Hello, ";
        if (name != null) {
            message += name + "!";
        } else {
            message += "Guest!";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/hello.jsp").forward(request, response);
    }
    // END
}
