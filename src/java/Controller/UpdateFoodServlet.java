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

public class UpdateFoodServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int inventory = Integer.parseInt(request.getParameter("inventory"));
            double price = Double.parseDouble(request.getParameter("price"));
            Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));
            int demand = Integer.parseInt(request.getParameter("demand"));
            boolean isDonation = request.getParameter("isDonation") != null;
            boolean isSurplus = request.getParameter("isSurplus") != null;

            Food food = new Food(id, name, inventory, price, expirationDate, demand, isDonation, isSurplus, 0); // Assumed retailer_id is not being updated
            FoodDAOImpl dao = new FoodDAOImpl();
            dao.updateFood(food); // Assume you have implemented this method

            response.sendRedirect("retailer_inventory.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("retailer_inventory.jsp?error=true");
        }
    }
}

