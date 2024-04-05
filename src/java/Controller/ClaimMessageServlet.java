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

public class ClaimMessageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            int messageId = Integer.parseInt(request.getParameter("messageId"));

            MessagesDAO dao = new MessagesDAO();
            if (dao.claimMessage(messageId, user.getId())) {
                // Optionally, send notification to the message owner
                response.sendRedirect("viewMessages.jsp?claimSuccess=true");
            } else {
                response.sendRedirect("viewMessages.jsp?error=true");
            }
        } else {
            // Not logged in or session expired
            response.sendRedirect("login.jsp");
        }
    }
}
