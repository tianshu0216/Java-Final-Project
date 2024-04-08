package Controller;

import dataaccesslayer.MessagesDAO;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet responsible for handling message posting functionality.
 * It allows logged-in users to post messages.
 */
public class PostMessageServlet extends HttpServlet {

    /**
     * Handles the HTTP {@code POST} request.
     * It receives a message from the user and attempts to save it via the {@link MessagesDAO}.
     * 
     * @param request  The {@link HttpServletRequest} object that contains the client's request.
     * @param response The {@link HttpServletResponse} object that contains the servlet's response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an input or output error is detected when the servlet handles the POST request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Attempt to retrieve the current session without creating a new one.
        HttpSession session = request.getSession(false);
        
        // Proceed if the session exists and contains a user object.
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            String message = request.getParameter("message");

            MessagesDAO dao = new MessagesDAO();
            // Attempt to post the message. Redirect based on the operation's success or failure.
            if (dao.postMessage(user.getId(), message)) {
                // On successful message posting, redirect to the messages view page with a success indicator.
                response.sendRedirect("viewMessages_consumer.jsp?success=true");
            } else {
                // On failure, redirect back to the message posting page with an error indicator.
                response.sendRedirect("postMessage.jsp?error=true");
            }
        } else {
            // If there is no valid session or user object, redirect to the login page.
            response.sendRedirect("login.jsp");
        }
    }
}
