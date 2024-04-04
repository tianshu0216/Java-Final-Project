/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

/**
 *
 * @author User
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Food;

public class FoodDAOImpl implements FoodDAO {

      private Connection connection;
    
     public FoodDAOImpl() 
    {
    	this.connection = DBConnection.getInstance().getConnection();
    }

     /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean addItem(Food food) {
        try {
            String query = "INSERT INTO food (name, inventory, price, expirationDate,demand, isDonation, isSurplus, retailer_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, food.getName());
            preparedStatement.setInt(2, food.getInventory());
            preparedStatement.setDouble(3, food.getPrice());
            preparedStatement.setDate(4, new java.sql.Date(food.getExpirationDate().getTime()));
            preparedStatement.setInt(5, food.getDemand());
            preparedStatement.setBoolean(6, food.getIsDonation());
            preparedStatement.setBoolean(7, food.getIsSurplus());
            preparedStatement.setInt(8, food.getRetailerId());
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateItemQuantity(int foodId, int new_inventory) {
        try {
            String query = "UPDATE food SET inventory = ? WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, new_inventory);
            preparedStatement.setInt(2, foodId);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Food> getSurplusItems() {
        List<Food> items = new ArrayList<>();
        try {
            String query = "SELECT * FROM food where isSurplus = true";
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Food food = new Food();
                food.setId(resultSet.getInt("id"));
                food.setName(resultSet.getString("name"));
                food.setInventory(resultSet.getInt("inventory"));
                food.setPrice(resultSet.getDouble("price"));
                food.setExpirationDate(resultSet.getDate("expirationDate"));
                food.setDemand(resultSet.getInt("demand"));
                food.setIsDonation(resultSet.getBoolean("isDonation"));
                food.setIsSurplus(resultSet.getBoolean("isSurplus"));
                items.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

//    @Override
//    public List<Food> getSurplusItems() {
//        // Implement logic to identify surplus items (items nearing expiration or excess of demand)
//        // For example, you can filter items based on expiration date
//        // You can modify the SQL query to retrieve items nearing expiration or in excess of demand
//        List<Food> surplusItems = new ArrayList<>();
//        try {
//            String query = "SELECT * FROM food WHERE expirationDate <= DATE_ADD(CURDATE(), INTERVAL 7 DAY)";
//            Statement statement = this.connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                Food item = new Food();
//                item.setId(resultSet.getInt("id"));
//                item.setName(resultSet.getString("name"));
//                item.setQuantity(resultSet.getInt("quantity"));
//                item.setPrice(resultSet.getBigDecimal("price"));
//                item.setExpirationDate(resultSet.getDate("expirationDate"));
//                surplusItems.add(item);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return surplusItems;
//    }

    @Override
    public Food getItemById(int itemId) {
        Food food = null;
        try {
            String query = "SELECT * FROM food WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, itemId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                food = new Food();
                food.setId(resultSet.getInt("id"));
                food.setName(resultSet.getString("name"));
                food.setInventory(resultSet.getInt("inventory"));
                food.setPrice(resultSet.getDouble("price"));
                food.setExpirationDate(resultSet.getDate("expirationDate"));
                food.setDemand(resultSet.getInt("demand"));
                food.setIsDonation(resultSet.getBoolean("isDonation"));
                food.setIsSurplus(resultSet.getBoolean("isSurplus"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return food;
    }

}
