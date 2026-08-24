/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.ReportDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Report;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "ReportManageServlet", urlPatterns = {"/admin/reports"})
public class ReportManageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        ReportDAO dao = new ReportDAO();
        List<Report> reports;

        if (status == null || status.trim().isEmpty()) {
            reports = dao.findAll();
        } else {
            reports = dao.findByStatus(status);
        }

        request.setAttribute("reports", reports);
        request.getRequestDispatcher("/admin/report-list.jsp").forward(request, response);
    }
}
