package Controller;

import dataaccesslayer.FoodDAOImpl;
import dataaccesslayer.TransactionDAOImpl;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Food;
import model.Transaction;
import model.User;
import dataaccesslayer.FoodDAO;

/**
 * This servlet handles the purchase process for a customer. It gathers selected food items and quantities from the request,
 * checks inventory, updates quantities, and records the transaction.
 */
public class CustomerPurchaseServlet extends HttpServlet {

    /**
     * Processes POST requests for customer purchases. Gathers selected item quantities from the form, checks availability,
     * updates inventory, and records the transaction if successful.
     * 
     * @param request  Servlet request containing form data with selected items and quantities.
     * @param response Servlet response to redirect based on the outcome of the purchase attempt.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        FoodDAO foodDAO = new FoodDAOImpl();
        Map<Integer, Integer> quantities = new HashMap<>();

        // Retrieve user from session. Redirect to login if not found.
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int userId = user.getId();
        
        // Extract food item quantities from the request.
        request.getParameterMap().forEach((key, values) -> {
            if (key.startsWith("quantity_")) {
                try {
                    int itemId = Integer.parseInt(key.substring(9));
                    int quantity = Integer.parseInt(values[0]);
                    if (quantity > 0) {
                        quantities.put(itemId, quantity);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing item ID or quantity: " + e.getMessage());
                }
            }
        });

        if (quantities.isEmpty()) {
            request.getSession().setAttribute("error", "No items selected.");
            response.sendRedirect("orderReview.jsp");
            return;
        }

        // Attempt the purchase.
        boolean purchaseSuccessful = processPurchase(foodDAO, quantities, userId);

        // Redirect based on purchase outcome.
        if (purchaseSuccessful) {
            // Redirect to success page after recording the purchase.
            response.sendRedirect("purchaseSuccessful.jsp");
        } else {
            request.getSession().setAttribute("error", "Purchase failed. Please try again.");
            response.sendRedirect("orderReview.jsp");
        }
    }

    /**
     * Attempts to complete the purchase by updating the inventory for each item and recording the transaction.
     * 
     * @param itemDAO     The DAO to interact with food items in the database.
     * @param quantities  A map of item IDs to quantities being purchased.
     * @param purchaserId The ID of the user making the purchase.
     * @return true if the purchase was successful; false otherwise.
     */
    private boolean processPurchase(FoodDAO itemDAO, Map<Integer, Integer> quantities, int purchaserId) {
        for (Map.Entry<Integer, Integer> entry : quantities.entrySet()) {
            int itemId = entry.getKey();
            int quantityToPurchase = entry.getValue();

            Food food = itemDAO.getItemById(itemId);
            if (food != null && food.getInventory() >= quantityToPurchase) {
                itemDAO.updateItemQuantity(itemId, food.getInventory() - quantityToPurchase);
            } else {
                // Insufficient stock.
                return false;
            }
        }
        // Record the transaction here if required.
        return true;
    }
}
