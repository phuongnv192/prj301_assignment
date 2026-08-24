/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.plant;

import dal.CareLogDAO;
import dal.PlantDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CareLog;
import model.Plant;
import model.User;

@WebServlet(name = "PlantDetailServlet", urlPatterns = {"/plants/detail"})
public class PlantDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User account = (User) session.getAttribute("account");
        int plantId = Integer.parseInt(request.getParameter("id"));

        PlantDAO plantDAO = new PlantDAO();
        Plant plant = "ADMIN".equals(account.getRole())
                ? plantDAO.findById(plantId)
                : plantDAO.findByIdAndUserId(plantId, account.getUserID());
        if (plant == null) {
            response.sendRedirect(request.getContextPath() + "/403.jsp");
            return;
        }

        List<CareLog> careLogs = new CareLogDAO().findByPlantId(plantId);
        request.setAttribute("plant", plant);
        request.setAttribute("careLogs", careLogs);
        request.getRequestDispatcher("/plant/detail.jsp").forward(request, response);
    }
}
