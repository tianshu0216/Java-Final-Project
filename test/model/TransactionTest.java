/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Timestamp;

/**
 *
 * @author Sahou
 */
public class TransactionTest {

    @Test
    public void testGetAndSetOrderId() {
        int expectedOrderId = 1;
        Transaction transaction = new Transaction();
        transaction.setOrderId(expectedOrderId);
        assertEquals(expectedOrderId, transaction.getOrderId());
    }

    @Test
    public void testGetAndSetQuantity() {
        int expectedQuantity = 10;
        Transaction transaction = new Transaction();
        transaction.setQuantity(expectedQuantity);
        assertEquals(expectedQuantity, transaction.getQuantity());
    }

    @Test
    public void testGetAndSetPurchaserId() {
        int expectedPurchaserId = 100;
        Transaction transaction = new Transaction();
        transaction.setPurchaserId(expectedPurchaserId);
        assertEquals(expectedPurchaserId, transaction.getPurchaserId());
    }

    @Test
    public void testGetAndSetItemInventoryId() {
        int expectedItemInventoryId = 500;
        Transaction transaction = new Transaction();
        transaction.setItemInventoryId(expectedItemInventoryId);
        assertEquals(expectedItemInventoryId, transaction.getItemInventoryId());
    }

    @Test
    public void testGetAndSetTransactionTime() {
        Timestamp expectedTransactionTime = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction();
        transaction.setTransactionTime(expectedTransactionTime);
        assertEquals(expectedTransactionTime, transaction.getTransactionTime());
    }

    @Test
    public void testGetAndSetItemName() {
        String expectedItemName = "Gadget";
        Transaction transaction = new Transaction();
        transaction.setItemName(expectedItemName);
        assertEquals(expectedItemName, transaction.getItemName());
    }

}
