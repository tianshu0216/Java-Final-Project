/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

/**
 *
 * @author Sahou
 */

package Controller;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dataaccesslayer.UserDAOImpl;
import model.User;

public class RegistrationFormServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserDAOImpl userDAO;
    private RegistrationFormServlet servlet;

    @Before
    public void setUp() {
        // Mock the necessary web objects
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userDAO = mock(UserDAOImpl.class);

        // Initialize the servlet and inject mocked UserDAO if possible, or use reflection if required
        servlet = new RegistrationFormServlet();

        // Stubbing to return the mocked session when requested
        when(request.getSession()).thenReturn(session);
    }

        @Test
    public void testUserAlreadyExists() throws Exception {
        // Mock the request to simulate a user with an existing email
        when(request.getParameter("email")).thenReturn("john@example.com");

        // Create a User object to simulate an existing user found in the database
        User existingUser = new User(
            1, // Mocked user ID
            "john", // Mocked user name
            "john@example.com", // Mocked user email
            "123456", // Mocked user password
            true, // Mocked subscription status
            "retailer", // Mocked user type
            "Ontario" // Mocked user location
        );

        // Stubbing DAO to simulate finding an existing user in the database
        when(userDAO.getUserByEmail("john@example.comm")).thenReturn(existingUser);

        // Call the method under test
        servlet.doPost(request, response);

        // Verify that the user is redirected to the registration page with an error
        verify(response).sendRedirect("http://localhost:8080/FWRP/user/registration.jsp?error=email_exists");
    }

    }
  
