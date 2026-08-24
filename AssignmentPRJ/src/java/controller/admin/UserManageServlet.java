/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.UserDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "UserManageServlet", urlPatterns = {"/admin/users"})
public class UserManageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        UserDAO dao = new UserDAO();

        if ("toggle".equals(action)) {
            String userIDParam = request.getParameter("userID");
            String statusParam = request.getParameter("status");
            if (userIDParam != null && statusParam != null) {
                int userID = Integer.parseInt(userIDParam);
                boolean status;
                if ("true".equalsIgnoreCase(statusParam) || "1".equals(statusParam)) {
                    status = true;
                } else if ("false".equalsIgnoreCase(statusParam) || "0".equals(statusParam)) {
                    status = false;
                } else {
                    status = !dao.findById(userID).isStatus();
                }
                dao.updateStatus(userID, status);
            }
            response.sendRedirect(request.getContextPath() + "/admin/users");
            return;
        }

        List<User> users = dao.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/admin/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        UserDAO dao = new UserDAO();

        if ("create".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String role = request.getParameter("role");

            if (username != null && password != null && fullName != null) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setFullName(fullName);
                user.setEmail(email);
                user.setRole(role);
                user.setStatus(true);
                dao.insert(user);
                response.sendRedirect(request.getContextPath() + "/admin/users?created=1");
                return;
            }
            request.setAttribute("error", "Please fill in all required fields.");
        } else if ("toggle".equals(action)) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String statusParam = request.getParameter("status");
            boolean status;
            if ("true".equalsIgnoreCase(statusParam) || "1".equals(statusParam)) {
                status = true;
            } else if ("false".equalsIgnoreCase(statusParam) || "0".equals(statusParam)) {
                status = false;
            } else {
                status = !dao.findById(userID).isStatus();
            }
            dao.updateStatus(userID, status);
            response.sendRedirect(request.getContextPath() + "/admin/users");
            return;
        }

        doGet(request, response);
    }
}
