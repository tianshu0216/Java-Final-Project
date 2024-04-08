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
 * Servlet implementation class responsible for handling message claim requests.
 * This servlet allows a logged-in user to claim a message based on the message ID.
 */
public class ClaimMessageServlet extends HttpServlet {
    
    /**
     * Processes the HTTP GET request to claim a message.
     * Retrieves the message ID from the request parameter and attempts to mark it as claimed by the current user.
     *
     * @param request  The {@link HttpServletRequest} object containing the client's request.
     * @param response The {@link HttpServletResponse} object containing the response the servlet sends to the client.
     * @throws ServletException if a servlet-specific error occurs during request processing.
     * @throws IOException if an I/O error occurs during request processing.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Attempt to retrieve the current session without creating a new one.
        HttpSession session = request.getSession(false);
        
        // Proceed if the session exists and contains a user object.
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            int messageId = Integer.parseInt(request.getParameter("messageId"));

            // Instantiate DAO to interact with the database.
            MessagesDAO dao = new MessagesDAO();
            
            // Attempt to claim the message. Redirect based on the operation's success or failure.
            if (dao.claimMessage(messageId, user.getId())) {
                // Redirect to the messages view page with a success indicator.
                response.sendRedirect("viewMessages.jsp?claimSuccess=true");
            } else {
                // Redirect back to the message view page with an error indicator.
                response.sendRedirect("viewMessages.jsp?error=true");
            }
        } else {
            // If there is no valid session or user object, redirect to the login page.
            response.sendRedirect("login.jsp");
        }
    }

    /**
     * Provides a short description of the servlet.
     * Useful for documentation and administrative purposes.
     *
     * @return A String containing a brief description of the servlet's purpose and functionality.
     */
    @Override
    public String getServletInfo() {
        return "Servlet handles the claiming of messages by users.";
    }
}
