/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.plant;

import dal.PlantDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Plant;
import model.User;

@WebServlet(name = "PlantListServlet", urlPatterns = {"/plants"})
public class PlantListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User account = (User) session.getAttribute("account");
        int userId = account.getUserID();

        PlantDAO plantDAO = new PlantDAO();
        List<Plant> plants;
        if ("ADMIN".equals(account.getRole())) {
            plants = plantDAO.findAll();
        } else {
            plants = plantDAO.findByUserId(userId);
        }
        request.setAttribute("plants", plants);
        request.getRequestDispatcher("/plant/list.jsp").forward(request, response);
    }
}
