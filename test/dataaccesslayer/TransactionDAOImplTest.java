/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
/**
 *
 * @author Sahou
 */


package dataaccesslayer;

import java.util.Collections;
import java.util.List;
import model.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TransactionDAOImplTest {
    
    public TransactionDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTransactionsByPurchaserEmail method, of class TransactionDAOImpl.
     */
    @Test
public void testGetTransactionsByPurchaserEmail() {
    System.out.println("getTransactionsByPurchaserEmail");
    String email = "";
    TransactionDAOImpl instance = new TransactionDAOImpl();
    List<Transaction> expResult = Collections.emptyList(); // Expecting an empty list
    List<Transaction> result = instance.getTransactionsByPurchaserEmail(email);
    assertEquals(expResult, result);
}

    /**
     * Test of getTransactionsByOwnerEmail method, of class TransactionDAOImpl.
     */
    @Test
public void testGetTransactionsByOwnerEmail() {
    System.out.println("getTransactionsByOwnerEmail");
    String email = "";
    TransactionDAOImpl instance = new TransactionDAOImpl();
    List<Transaction> expResult = Collections.emptyList(); // Expect an empty list instead of null
    List<Transaction> result = instance.getTransactionsByOwnerEmail(email);
    assertEquals(expResult, result);
}

    /**
     * Test of createTransaction method, of class TransactionDAOImpl.
     */
@Test
public void testCreateTransaction() {
    System.out.println("createTransaction");
    
    // Set up the transaction to be created
    Transaction transaction = new Transaction();
    transaction.setItemInventoryId(1);  // Assuming there's a method to set the inventory ID
    // Set other necessary fields of the transaction object
    
    TransactionDAOImpl instance = new TransactionDAOImpl();
    
    // Attempt to create the transaction
    try {
        instance.createTransaction(transaction);
        // If no exception is thrown, we assume success for this test case
    } catch (Exception e) {
        fail("Transaction creation failed with an exception: " + e.getMessage());
    }
    }
}



    

