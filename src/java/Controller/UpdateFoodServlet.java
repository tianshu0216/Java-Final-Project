/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dataaccesslayer.FoodDAOImpl;
import model.Food;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

/**
 * Servlet to handle updating food item details in the inventory.
 */
public class UpdateFoodServlet extends HttpServlet {

    /**
     * Handles the HTTP POST request.
     * Updates the food item based on the form parameters submitted.
     *
     * @param request  The servlet request that contains form data to update a food item.
     * @param response The servlet response to redirect after processing the request.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error related to servlet processing occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Food food = extractFoodFromRequest(request);
            updateFoodItem(food);
            redirectSuccess(response);
        } catch (NumberFormatException e) {
            redirectWithError(response);
        }
    }

    /**
     * Extracts food item details from the request and constructs a food model object.
     *
     * @param request The servlet request to extract food item parameters.
     * @return Food model constructed from request parameters.
     */
    private Food extractFoodFromRequest(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int inventory = Integer.parseInt(request.getParameter("inventory"));
        double price = Double.parseDouble(request.getParameter("price"));
        Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));
        int demand = Integer.parseInt(request.getParameter("demand"));
        boolean isDonation = request.getParameter("isDonation") != null;
        // Assumed retailer_id is not being updated, set to 0 as a placeholder
        return new Food(id, name, inventory, price, expirationDate, demand, isDonation, 0);
    }

    /**
     * Updates the food item using the DAO implementation.
     *
     * @param food The food item to be updated in the inventory.
     */
    private void updateFoodItem(Food food) {
        FoodDAOImpl dao = new FoodDAOImpl();
        dao.updateFood(food); // Method to be implemented in FoodDAOImpl
    }

    /**
     * Redirects to the inventory page upon successful update.
     *
     * @param response The servlet response to redirect.
     * @throws IOException If an I/O error occurs during redirection.
     */
    private void redirectSuccess(HttpServletResponse response) throws IOException {
        response.sendRedirect("retailer_inventory.jsp");
    }

    /**
     * Redirects to the inventory page with an error flag upon failure.
     *
     * @param response The servlet response to redirect.
     * @throws IOException If an I/O error occurs during redirection.
     */
    private void redirectWithError(HttpServletResponse response) throws IOException {
        response.sendRedirect("retailer_inventory.jsp?error=true");
    }
}


