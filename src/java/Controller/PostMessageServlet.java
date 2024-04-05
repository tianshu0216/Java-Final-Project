/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dataaccesslayer.MessagesDAO;
import model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class PostMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            String message = request.getParameter("message");

            MessagesDAO dao = new MessagesDAO();
            if (dao.postMessage(user.getId(), message)) {
                response.sendRedirect("viewMessages_consumer.jsp?success=true");
            } else {
                response.sendRedirect("postMessage.jsp?error=true");
            }
        } else {
            // Not logged in or session expired
            response.sendRedirect("login.jsp");
        }
    }
}
