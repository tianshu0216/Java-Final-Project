/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

/**
 *
 * @author Tianshu
 */
import java.util.List;
import model.Food;
        
public interface FoodDAO {
    boolean addItem(Food item);
    boolean updateItemQuantity(int itemId, int newQuantity);
//    List<Food> getItems();
    List<Food> getSurplusItems();
    Food getItemById(int itemId);
    List<Food> getFoodsByRetailerId(int userId);
    boolean updateFood(Food food);
}
