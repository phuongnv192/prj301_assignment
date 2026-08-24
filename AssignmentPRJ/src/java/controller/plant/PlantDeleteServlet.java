/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.plant;

import dal.PlantDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Plant;
import model.User;

@WebServlet(name = "PlantDeleteServlet", urlPatterns = {"/plants/delete"})
public class PlantDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User account = (User) session.getAttribute("account");
        int plantId = Integer.parseInt(request.getParameter("id"));
        PlantDAO plantDAO = new PlantDAO();
        if ("ADMIN".equals(account.getRole())) {
            Plant plant = plantDAO.findById(plantId);
            if (plant != null) {
                plantDAO.delete(plantId, plant.getOwner().getUserID());
            }
        } else {
            plantDAO.delete(plantId, account.getUserID());
        }
        response.sendRedirect(request.getContextPath() + "/plants");
    }
}
