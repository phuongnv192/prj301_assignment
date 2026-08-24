/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.CareLogDAO;
import dal.PlantDAO;
import dal.ReportDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CareLog;
import model.User;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "CustomerHomeServlet", urlPatterns = {"/customer/home"})
public class CustomerHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User account = (User) session.getAttribute("account");
        int userId = account.getUserID();

        PlantDAO plantDAO = new PlantDAO();
        ReportDAO reportDAO = new ReportDAO();
        CareLogDAO careLogDAO = new CareLogDAO();

        request.setAttribute("plantCount", plantDAO.countByUserId(userId));
        request.setAttribute("pendingReportCount", reportDAO.countPendingByUserId(userId));
        List<CareLog> careLogs = careLogDAO.findByUserId(userId);
        request.setAttribute("recentCareLogs", careLogs);
        request.getRequestDispatcher("/customer/home.jsp").forward(request, response);
    }
}
