package Controller;

import dataaccesslayer.FoodDAOImpl;
import model.Food;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class for listing items.
 * This servlet handles the retrieval and display of items from the inventory.
 */
public class ItemListServlet extends HttpServlet {

    /**
     * Processes the HTTP GET request to fetch and display a list of items.
     * It retrieves surplus food items from the database and forwards them to a JSP page for display.
     *
     * @param request  The servlet request object containing the request made by the client.
     * @param response The servlet response object containing the response the servlet sends to the client.
     * @throws ServletException if a servlet-specific error occurs during request processing.
     * @throws IOException if an I/O error occurs during request processing.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Instantiate the DAO implementation to access the database.
        FoodDAOImpl foodDAO = new FoodDAOImpl();

        // Retrieve the list of surplus food items from the database.
        List<Food> items = foodDAO.getSurplusItems();

        // Log the items retrieved for debugging purposes.
        System.out.println("Items retrieved: " + items);

        // Attach the list of items to the request object to make it available to the JSP.
        request.setAttribute("items", items);

        // Forward the request to the JSP page for rendering the list of items.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/charity/charity.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * This description should succinctly summarize the servlet's functionality and purpose.
     *
     * @return A String containing a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Servlet retrieves and displays a list of surplus food items.";
    }
}
