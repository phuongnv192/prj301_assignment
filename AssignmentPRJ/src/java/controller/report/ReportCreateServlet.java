/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.report;

import dal.PlantDAO;
import dal.ReportDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Plant;
import model.Report;
import model.User;

@WebServlet(name = "ReportCreateServlet", urlPatterns = {"/reports/create"})
public class ReportCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User account = (User) session.getAttribute("account");
        int plantId = Integer.parseInt(request.getParameter("plantId"));
        PlantDAO plantDAO = new PlantDAO();
        Plant plant = "ADMIN".equals(account.getRole())
                ? plantDAO.findById(plantId)
                : plantDAO.findByIdAndUserId(plantId, account.getUserID());
        if (plant == null) {
            response.sendRedirect(request.getContextPath() + "/403.jsp");
            return;
        }

        request.setAttribute("plantID", plantId);
        request.getRequestDispatcher("/report/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User account = (User) session.getAttribute("account");
        int plantId = Integer.parseInt(request.getParameter("plantID"));
        PlantDAO plantDAO = new PlantDAO();
        Plant plant = "ADMIN".equals(account.getRole())
                ? plantDAO.findById(plantId)
                : plantDAO.findByIdAndUserId(plantId, account.getUserID());
        if (plant == null) {
            response.sendRedirect(request.getContextPath() + "/403.jsp");
            return;
        }

        Report report = new Report();
        User reportUser = new User();
        reportUser.setUserID(account.getUserID());
        report.setUser(reportUser);
        Plant reportPlant = new Plant();
        reportPlant.setPlantID(plantId);
        report.setPlant(reportPlant);
        report.setTitle(request.getParameter("title"));
        report.setDescription(request.getParameter("description"));
        new ReportDAO().insert(report);
        response.sendRedirect(request.getContextPath() + "/reports/my");
    }
}
