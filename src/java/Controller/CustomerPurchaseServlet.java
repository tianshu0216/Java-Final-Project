/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * Servlet implementation class CustomerPurchaseServlet
 * 
 * This servlet handles the purchase process initiated by the customer.
 */

public class CustomerPurchaseServlet extends HttpServlet {

     /**
     * Handles the HTTP <code>POST</code> method.
     * 
     * @param request  the request object containing customer's purchase details
     * @param response the response object to be sent back to the client
     * 
     * @throws ServletException if servlet-related errors occur
     * @throws IOException      if an input or output exception occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CustomerPurchaseServlet doPost called");
        FoodDAO foodDAO = new FoodDAOImpl();
        Map<Integer, Integer> quantities = new HashMap<>();

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            
            response.sendRedirect("login.jsp"); 
            return;
        }
        int userId = user.getId();
        System.out.println("Using userId: " + user.getName());
        System.out.println("Using userId: " + userId);
        
        request.getParameterMap().forEach((key, values) -> {
            if (key.startsWith("quantity_")) {
                try {
                    int itemId = Integer.parseInt(key.substring(9)); 
                    int quantity = Integer.parseInt(values[0]); 
                    if (quantity > 0) { 
                        quantities.put(itemId, quantity);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing item ID or quantity");
                }
            }
        });

       
        if (quantities.isEmpty()) {
            request.getSession().setAttribute("error", "No items selected.");
            response.sendRedirect("orderReview.jsp"); // Redirect back to review page
            return;
        }

      
        boolean purchaseSuccessful = processPurchase(foodDAO, quantities);

        if (purchaseSuccessful) {
          
            int confirmationNumber = (int) (Math.random() * 1000000); 
            request.getSession().setAttribute("customerconfirmationNumber", Integer.toString(confirmationNumber));
            int totalQuantity = 0;
            Map<String, Double> lineTotals = new HashMap<>();
            Map<String, Integer> purchasedItemsInfo = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : quantities.entrySet()) {
                Food food = foodDAO.getItemById(entry.getKey());
                if (food != null) {
                    purchasedItemsInfo.put(food.getName(), entry.getValue());
                
                     
                        Transaction transaction = new Transaction();
                        transaction.setOrderId(confirmationNumber);
                        transaction.setQuantity(entry.getValue());
                        transaction.setPurchaserId(userId);
                        transaction.setItemInventoryId(entry.getKey());
                        transaction.setTransactionTime(new java.sql.Timestamp(new java.util.Date().getTime()));
                        TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
                        transactionDAO.createTransaction(transaction);
                }
                double totalPrice = 0;
                double lineTotal = food.getPrice()* entry.getValue();
                lineTotals.put(food.getName(), lineTotal);
                totalQuantity += entry.getValue();
                totalPrice = totalPrice + lineTotal;
                request.getSession().setAttribute("lineTotals", lineTotals);
                request.getSession().setAttribute("totalQuantity", totalQuantity);
                request.getSession().setAttribute("totalPrice", totalPrice);

            }
             request.getSession().setAttribute("customerordertime", new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
            request.getSession().setAttribute("customerpurchasedItems", purchasedItemsInfo);
            response.sendRedirect("purchaseSuccessful.jsp"); 
        } else {
            
            request.getSession().setAttribute("error", "Purchase failed. Please try again.");
            response.sendRedirect("orderReview.jsp"); 
        }
    }

        /**
     * Process the purchase of items by updating item quantities in the database.
     * 
     * @param itemDAO    the FoodDAO object used to interact with the database
     * @param quantities a map containing item IDs and quantities to be purchased
     * 
     * @return true if the purchase process was successful, otherwise false
     */
    private boolean processPurchase(FoodDAO itemDAO, Map<Integer, Integer> quantities) {
        
        for (Map.Entry<Integer, Integer> entry : quantities.entrySet()) {
            int itemId = entry.getKey();
            int quantityToPurchase = entry.getValue();

            Food food = itemDAO.getItemById(itemId);
            if (food != null && food.getInventory() >= quantityToPurchase) {
               
                itemDAO.updateItemQuantity(itemId, food.getInventory() - quantityToPurchase);
            } else {
               
                return false;
            }
        }
        return true;
    }
}
