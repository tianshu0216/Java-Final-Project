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

public class ItemListCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        System.out.println("ItemListCustomerServlet doGet called");
        
        // Fetch surplus food items and set them as a request attribute
        setSurplusItemsInRequest(request);

        // Dispatch the request to customer.jsp for displaying the items
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/customer.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * Fetches surplus food items from the database and sets them as a request attribute.
     * 
     * @param request The HTTPServletRequest to which the items attribute will be added.
     */
    private void setSurplusItemsInRequest(HttpServletRequest request) {
        FoodDAOImpl foodDAO = new FoodDAOImpl();
        List<Food> foods = foodDAO.getSurplusItems();
        System.out.println("Items retrieved: " + foods);
        request.setAttribute("items", foods);
    }

    @Override
    public String getServletInfo() {
        return "Servlet retrieves and forwards surplus food items to the customer view.";
    }
}
