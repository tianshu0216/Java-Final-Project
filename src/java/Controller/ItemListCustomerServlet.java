/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dataaccesslayer.FoodDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Food;


public class ItemListCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ItemListCustomerServlet doGet called");
        FoodDAOImpl foodDAO = new FoodDAOImpl();
        List<Food> foods = foodDAO.getSurplusItems(); 
        System.out.println("Items retrieved: " + foods);
        request.setAttribute("items", foods); 

        // Forward to consumers.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/customer.jsp");
        dispatcher.forward(request, response);
    }
}
