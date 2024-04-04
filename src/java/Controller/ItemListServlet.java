/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Qina&Kaiwen
 */
import dataaccesslayer.FoodDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Food;


public class ItemListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ItemListServlet doGet called");
        FoodDAOImpl foodDAO = new FoodDAOImpl();
        List<Food> items = foodDAO.getSurplusItems(); // Fetch the list of items
        System.out.println("Items retrieved: " + items);
        request.setAttribute("items", items); // Store it as a request attribute

        // Forward to consumers.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/charity/charity.jsp");
        dispatcher.forward(request, response);
    }
}
