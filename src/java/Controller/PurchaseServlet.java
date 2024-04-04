/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Qina&Kaiwen
 */
import dataaccesslayer.DBConnection;
import dataaccesslayer.FoodDAOImpl;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Food;
import dataaccesslayer.TransactionDAOImpl;
import model.Transaction;
import model.User;
import dataaccesslayer.FoodDAO;

public class PurchaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("PurchaseServlet doPost called");
        FoodDAO foodDAO = new FoodDAOImpl();
        // STEP 1: Fetch the list of all foods Store food ID and quantity
        List<Food> foods = foodDAO.getSurplusItems();
        Map<Integer, Integer> quantities = new HashMap<>();

        boolean allItemsAvailable = false;
        boolean purchaseSuccessful = false;

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            // Handle the case where the user is not logged in (e.g., redirect to login page)
            response.sendRedirect("login.jsp"); // or wherever your login page is
            return;
        }
        int userId = user.getId();
        System.out.println("Using userId: " + user.getName());
        System.out.println("Using userId: " + userId);
        
        try {

            //STEP 2： Extract food quantities from request.
            for (Food food : foods) {
                String quantityParam = "quantity_" + food.getId();
                String quantityString = request.getParameter(quantityParam);
                if (quantityString != null && !quantityString.isEmpty()) {
                    int quantity = Integer.parseInt(quantityString);
                    // Add to the map if the quantity is greater than 0
                    if (quantity > 0) {
                        quantities.put(food.getId(), quantity);
                        System.out.println("Item ID: " + food.getId() + ", Quantity: " + quantity);
                    }
                }
            }
            //STEP 3： No food selected situation
            boolean anyQuantitySelected = quantities.values().stream().anyMatch(qty -> qty > 0);
            if (!anyQuantitySelected) {
                request.getSession().setAttribute("error", "You have not selected any items.");
                response.sendRedirect("ItemListServlet");
            }
            //STEP 4 ： Check each food's availability
            for (Map.Entry<Integer, Integer> entry : quantities.entrySet()) {
                int itemId = entry.getKey();
                int requestedQuantity = entry.getValue();
                // Fetch the current stock from the database
                Food food = foodDAO.getItemById(itemId);
                if (food == null || food.getInventory() < requestedQuantity) {
                    System.out.println("Insufficient stock for Item ID: " + itemId);
                    break;
                } else {
                    allItemsAvailable = true;
                }
            }

            //STEP 5 ： Update Inventory
            if (allItemsAvailable) {
                quantities.forEach((itemId, quantity) -> {
                    Food item = foodDAO.getItemById(itemId);
                    foodDAO.updateItemQuantity(itemId, item.getInventory() - quantity);

                });
                purchaseSuccessful = true;

            } else {
                purchaseSuccessful = false;
                response.getWriter().write("Purchase failed, please have another try!");

            }
            if (purchaseSuccessful) {
                // Prepare data to send back
                int confirmationNumber = (int) (Math.random() * 1000000);

                // Convert purchasedItems map from ID-based to name-based for user readability
                Map<String, Integer> purchasedItemsInfo = new HashMap<>();
                for (Map.Entry<Integer, Integer> entry : quantities.entrySet()) {
                    Food item = foodDAO.getItemById(entry.getKey());
                    if (item != null) {
                        purchasedItemsInfo.put(item.getName(), entry.getValue());

                        // Create a new transaction object for each food
                        Transaction transaction = new Transaction();
                        transaction.setOrderId(confirmationNumber);
                        transaction.setQuantity(entry.getValue());
                        transaction.setPurchaserId(userId);
                        transaction.setItemInventoryId(entry.getKey());
                        transaction.setTransactionTime(new java.sql.Timestamp(new java.util.Date().getTime()));
                        TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
                        transactionDAO.createTransaction(transaction);
                    }
                }

                // Store purchase summary in the session
                request.getSession().setAttribute("confirmationNumber", Integer.toString(confirmationNumber));
                request.getSession().setAttribute("purchasedItems", purchasedItemsInfo);
                request.getSession().setAttribute("charityordertime", new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
                System.out.println("Purchased Items: " + purchasedItemsInfo.toString());
                response.sendRedirect("orderConfirmation.jsp");

            } else {

                response.setCharacterEncoding("UTF-8");

            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.err.println("Error parsing quantities: " + e.getMessage());
            response.getWriter().write("Purchase failed: Invalid quantity format.");
        } catch (Exception e) {
            System.err.println("Purchase process failed: " + e.getMessage());
            e.printStackTrace();
            response.getWriter().write("Purchase failed due to an error: " + e.getMessage());
        }

    }
}
