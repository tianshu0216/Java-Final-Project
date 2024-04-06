package Controller;

import dataaccesslayer.DBConnection;
import dataaccesslayer.FoodDAOImpl;
import model.Food;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



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
      //  boolean isSurplus = quantity > 1.5 * demand;

        Food food = new Food();
        food.setName(itemName);
        food.setInventory(quantity);
        food.setPrice(price);
        food.setDemand(demand);
        food.setExpirationDate(expirationDate);
        food.setIsDonation(isDonation);
//        food.setIsSurplus(isSurplus);
        food.setRetailerId(user.getId());
        System.out.println(user.getId());
        
        
        // For notification
        String location = user.getLocation();
        
        PreparedStatement pstmt = null;
        

        //get user with or location
        try (Connection connection = DBConnection.getInstance().getConnection()){
             if(food.getIsDonation()){
             pstmt = connection.prepareStatement("SELECT DISTINCT user.email FROM user WHERE userType = 'charitable organization'  AND isSubscribe = true AND location = ?");}else{
             pstmt = connection.prepareStatement("SELECT DISTINCT user.email FROM user WHERE userType = 'consumer'  AND isSubscribe = true AND location = ?");}
            pstmt.setString(1, location);
            StringBuilder emailsSent = new StringBuilder();
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String receiverEmail = rs.getString("user.email");                       
                    System.out.println("Email sent successfully to " + receiverEmail);
                    System.out.println("New surplus food available");
                    
                    emailsSent.append(receiverEmail).append("\\n");

                }
                
            }
            String message = "Email sent successfully to:\\n" + emailsSent.toString();
            request.setAttribute("alertEmailMessage", message);
        } catch (Exception e) {
            e.printStackTrace();
            
        }

        FoodDAOImpl dao = new FoodDAOImpl();
        if (dao.addItem(food)) {
            request.getRequestDispatcher("retail_add.jsp").forward(request, response);
        } else {
            response.sendRedirect("retail_add.jsp?error=true");
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    response.sendRedirect("retail_add.jsp");
    
}
}
