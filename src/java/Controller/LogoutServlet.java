package Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class for handling user logout requests.
 * This servlet manages the termination of a user's session by removing user information from the session.
 */
public class LogoutServlet extends HttpServlet {
    /**
     * Processes the HTTP {@code GET} request to log out a user.
     * The method invalidates the user session and redirects the user to the homepage.
     *
     * @param request  The servlet request object that contains the request made by the client.
     * @param response The servlet response object that contains the response the servlet sends to the client.
     * @throws ServletException If a servlet-specific error occurs during request processing.
     * @throws IOException      If an I/O error occurs during request processing.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logging the receipt of a GET request for logging out.
        System.out.print("Receiving GET request for logout.");

        // Invalidate the user session to effectively log out the user.
        request.getSession().invalidate();

        // Redirect the user to the application's root (homepage) after successful logout.
        response.sendRedirect("http://localhost:8080/FWRP");
    }

    /**
     * Returns a short description of the servlet.
     * This can be used to describe the servlet's purpose and main functionalities.
     *
     * @return A String containing a brief description of the servlet's behavior.
     */
    @Override
    public String getServletInfo() {
        return "Handles user logout requests by invalidating the current session and redirecting to the homepage.";
    }
}
