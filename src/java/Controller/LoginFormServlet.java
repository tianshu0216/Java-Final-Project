package Controller;

import dataaccesslayer.UserDAOImpl;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class for handling user login requests.
 * This servlet processes login attempts and manages session attributes based on authentication success.
 */
public class LoginFormServlet extends HttpServlet {

    /**
     * Processes the HTTP POST method for user login requests.
     * Authenticates the user based on email and password, and redirects to the dashboard on success or the login page on failure.
     *
     * @param request  The servlet request that contains the login details.
     * @param response The servlet response to redirect based on login success or failure.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User authenticatedUser = authenticateUser(request);
        
        if (authenticatedUser != null) {
            successfulLoginActions(request, response, authenticatedUser);
        } else {
            failedLoginActions(response);
        }
    }

    /**
     * Authenticates a user based on the provided credentials in the request.
     *
     * @param request The servlet request containing login details.
     * @return The authenticated User object, or null if authentication fails.
     */
    private User authenticateUser(HttpServletRequest request) {
        UserDAOImpl userDAO = new UserDAOImpl();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        User userFound = userDAO.getUserByEmail(email);
        
        if (userFound != null && userFound.getPassword().equals(password)) {
            return userFound;
        }
        
        return null;
    }

    /**
     * Manages actions to be taken upon successful login, including setting session attributes and redirecting to the user dashboard.
     *
     * @param request         The servlet request object.
     * @param response        The servlet response object.
     * @param authenticatedUser The authenticated User object.
     * @throws IOException if an I/O error occurs during redirection.
     */
    private void successfulLoginActions(HttpServletRequest request, HttpServletResponse response, User authenticatedUser)
            throws IOException {
        request.getSession().setAttribute("user", authenticatedUser);
        response.sendRedirect("http://localhost:8080/FWRP/user/dashboard.jsp");
    }

    /**
     * Manages actions to be taken upon failed login, including redirecting to the login page with an error message.
     *
     * @param response The servlet response object.
     * @throws IOException if an I/O error occurs during redirection.
     */
    private void failedLoginActions(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8080/FWRP/user/login.jsp?error=invalid");
    }

    /**
     * Returns a short description of the servlet.
     * The description should succinctly describe the servlet's purpose and how it processes requests.
     *
     * @return A String containing the servlet description.
     */
    @Override
    public String getServletInfo() {
        return "Servlet handles user login requests and manages user session on successful authentication.";
    }
}
