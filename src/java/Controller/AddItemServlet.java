package Controller;

import dataaccesslayer.FoodDAOImpl;
import model.Food;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;



public class AddItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String itemName = request.getParameter("itemName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        int demand = Integer.parseInt(request.getParameter("demand"));
        Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));
        boolean isDonation = request.getParameter("isDonation") != null;
        boolean isSurplus = quantity > 1.5 * demand;

        Food food = new Food();
        food.setName(itemName);
        food.setInventory(quantity);
        food.setPrice(price);
        food.setDemand(demand);
        food.setExpirationDate(expirationDate);
        food.setIsDonation(isDonation);
//        food.setIsSurplus(isSurplus);
        food.setRetailerId(user.getId());

        FoodDAOImpl dao = new FoodDAOImpl();
        if (dao.addItem(food)) {
            response.sendRedirect("retail_add.jsp?success=true");
        } else {
            response.sendRedirect("retail_add.jsp?error=true");
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect("retail_add.jsp");
    
}
}
