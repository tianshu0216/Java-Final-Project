package Controller;

import dataaccesslayer.TransactionDAO;
import dataaccesslayer.TransactionDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Transaction;
import model.User;

/**
 * This servlet handles the retrieval of transaction history for the logged-in user.
 */
public class TransactionServlet extends HttpServlet {

    /**
     * Responds to GET requests by retrieving transaction history for the user.
     *
     * @param request  The servlet request object containing the user's session and query parameters.
     * @param response The servlet response object to redirect or forward the request.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        log("TransactionServlet doGet called");
        
        User user = getCurrentUser(request);
        
        if (isUserNotLoggedIn(user)) {
            redirectToLogin(response);
        } else {
            List<Transaction> transactions = fetchUserTransactions(user);
            forwardToTransactionPage(request, response, transactions);
        }
    }

    /**
     * Logs a message to the console. This method can be enhanced to log to a file or logging framework.
     *
     * @param message The message to log.
     */
    public void log(String message) {
        System.out.println(message);
    }

    /**
     * Retrieves the current logged-in user from the session.
     *
     * @param request The servlet request object.
     * @return The logged-in user or null if no user is found.
     */
    private User getCurrentUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    /**
     * Checks if the user is not logged in.
     *
     * @param user The user object to check.
     * @return true if the user is not logged in, false otherwise.
     */
    private boolean isUserNotLoggedIn(User user) {
        return user == null;
    }

    /**
     * Redirects the response to the login page.
     *
     * @param response The servlet response object.
     * @throws IOException If an input or output exception occurs
     */
    private void redirectToLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("login.jsp"); // Redirect to login page
    }

    /**
     * Fetches the transaction history for the given user.
     *
     * @param user The user whose transaction history is to be retrieved.
     * @return A list of transactions associated with the user.
     */
    private List<Transaction> fetchUserTransactions(User user) {
        TransactionDAO transactionDAO = new TransactionDAOImpl();
        return transactionDAO.getTransactionsByPurchaserEmail(user.getEmail());
    }

    /**
     * Forwards the request to the transaction history page along with the transactions data.
     *
     * @param request       The servlet request object.
     * @param response      The servlet response object.
     * @param transactions  The transactions to display on the page.
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    private void forwardToTransactionPage(HttpServletRequest request, HttpServletResponse response, 
                                          List<Transaction> transactions) 
                                          throws ServletException, IOException {
        log("History retrieved: " + transactions);
        request.setAttribute("transactions", transactions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/transaction.jsp");
        dispatcher.forward(request, response);
    }
}
