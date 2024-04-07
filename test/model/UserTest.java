/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

/**
 *
 * @author Sahou
 */

package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



public class UserTest {

    public UserTest() {
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

    // Example of a properly set-up test
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expectedName = "Test User";
        User instance = new User(expectedName, "test@example.com", "password", true, "type", "location");
        String result = instance.getName();
        assertEquals(expectedName, result);
    }

    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expectedEmail = "test@example.com";
        User instance = new User("Test User", expectedEmail, "password", true, "type", "location");
        String result = instance.getEmail();
        assertEquals(expectedEmail, result);
    }

    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String expectedPassword = "password";
        User instance = new User("Test User", "test@example.com", expectedPassword, true, "type", "location");
        String result = instance.getPassword();
        assertEquals(expectedPassword, result);
    }

    @Test
    public void testGetUserType() {
        System.out.println("getUserType");
        String expectedUserType = "type";
        User instance = new User("Test User", "test@example.com", "password", true, expectedUserType, "location");
        String result = instance.getUserType();
        assertEquals(expectedUserType, result);
    }

    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        String expectedLocation = "location";
        User instance = new User("Test User", "test@example.com", "password", true, "type", expectedLocation);
        String result = instance.getLocation();
        assertEquals(expectedLocation, result);
    }

    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String expectedEmail = "newemail@example.com";
        User instance = new User("Test User", "test@example.com", "password", true, "type", "location");
        instance.setEmail(expectedEmail);
        assertEquals(expectedEmail, instance.getEmail());
    }

    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String expectedPassword = "newpassword";
        User instance = new User("Test User", "test@example.com", "password", true, "type", "location");
        instance.setPassword(expectedPassword);
        assertEquals(expectedPassword, instance.getPassword());
    }

    @Test
    public void testSetUserType() {
        System.out.println("setUserType");
        String expectedUserType = "newType";
        User instance = new User("Test User", "test@example.com", "password", true, "type", "location");
        instance.setUserType(expectedUserType);
        assertEquals(expectedUserType, instance.getUserType());
    }

    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        String expectedLocation = "newLocation";
        User instance = new User("Test User", "test@example.com", "password", true, "type", "location");
        instance.setLocation(expectedLocation);
        assertEquals(expectedLocation, instance.getLocation());
    }

    
    @Test
    public void testSetId() {
        System.out.println("setId");
        int expectedId = 100;
        User instance = new User(1, "Test User", "test@example.com", "password", true, "type", "location");
        instance.setId(expectedId);
        assertEquals(expectedId, instance.getId());
    }
}
