/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
/**
 *
 * @author Sahou
 */

package model;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class FoodTest {

    @Test
    public void testGetAndSetId() {
        int expectedId = 1;
        Food food = new Food();
        food.setId(expectedId);
        assertEquals(expectedId, food.getId());
    }

    @Test
    public void testGetAndSetName() {
        String expectedName = "Apple";
        Food food = new Food();
        food.setName(expectedName);
        assertEquals(expectedName, food.getName());
    }

    @Test
    public void testGetAndSetInventory() {
        int expectedInventory = 100;
        Food food = new Food();
        food.setInventory(expectedInventory);
        assertEquals(expectedInventory, food.getInventory());
    }

    @Test
    public void testGetAndSetPrice() {
        double expectedPrice = 1.99;
        Food food = new Food();
        food.setPrice(expectedPrice);
        assertEquals(expectedPrice, food.getPrice(), 0.0);
    }

    @Test
    public void testGetAndSetExpirationDate() {
        Date expectedDate = new Date();
        Food food = new Food();
        food.setExpirationDate(expectedDate);
        assertEquals(expectedDate, food.getExpirationDate());
    }

    @Test
    public void testGetAndSetDemand() {
        int expectedDemand = 50;
        Food food = new Food();
        food.setDemand(expectedDemand);
        assertEquals(expectedDemand, food.getDemand());
    }

    @Test
    public void testGetAndSetIsDonation() {
        Boolean expectedIsDonation = true;
        Food food = new Food();
        food.setIsDonation(expectedIsDonation);
        assertEquals(expectedIsDonation, food.getIsDonation());
    }

    @Test
    public void testGetAndSetRetailerId() {
        int expectedRetailerId = 2;
        Food food = new Food();
        food.setRetailerId(expectedRetailerId);
        assertEquals(expectedRetailerId, food.getRetailerId());
    }

}
