/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dataaccesslayer.UserDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 * Servlet implementation class RegistrationFormServlet.
 * This servlet handles user registration by processing POST requests with user details.
 */
public class RegistrationFormServlet extends HttpServlet {
     /**
     * Handles the HTTP {@code POST} method by registering a new user with the information provided
     * through the form submission.
     *
     * @param request  the {@link HttpServletRequest} object that contains the request the client
     *                 has made to the servlet
     * @param response the {@link HttpServletResponse} object that contains the response the servlet
     *                 sends to the client
     * @throws ServletException if an error occurs during the servlet processing
     * @throws IOException      if an I/O error occurs during servlet processing
     */
   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    

    // Extract form data to create a new user
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String userType = request.getParameter("userType");
    boolean isSubscribe = Boolean.parseBoolean(request.getParameter("isSubscribe"));
    String location = request.getParameter("location");
    // Instantiate a new User object with the form data
    User newUser = new User(name, email, password,  isSubscribe ,userType, location);
    UserDAOImpl userDAO = new UserDAOImpl();
    // Check if the user already exists in the database by email
    if (userDAO.getUserByEmail(email) != null) {
        System.out.println("User email already exists in the database.");
        response.sendRedirect("http://localhost:8080/FWRP/user/registration.jsp?error=email_exists");
        return; 
    }
    
    if (userDAO.createUser(newUser) > 0) {
        System.out.println("New user registration successful.");
        request.getSession().setAttribute("user", newUser);
        // Redirect to management.html upon successful registration
        response.sendRedirect("http://localhost:8080/FWRP/user/dashboard.jsp");           
    } else {
        System.out.println("User registration failed.");
        // Redirect to login page with an alert
        response.sendRedirect("http://localhost:8080/FWRP/user/registration.jsp?error=fail");
        
    }
}


     /**
     * Returns a short description of the servlet. This description should be a concise summary
     * of the servlet's functionality and purpose.
     *
     * @return a String containing a short description of the servlet
     */
    @Override
    public String getServletInfo() {
        return "RegistrationFormServlet handles new user registrations.";
    }

    
}
