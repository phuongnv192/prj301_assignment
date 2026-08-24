/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.ReportDAO;
import java.io.IOException;
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
@WebServlet(name = "ReportReplyServlet", urlPatterns = {"/admin/reports/reply"})
public class ReportReplyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Report report = new ReportDAO().findById(id);
        request.setAttribute("report", report);
        request.getRequestDispatcher("/admin/report-reply.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int reportID = Integer.parseInt(request.getParameter("reportID"));
        ReportDAO dao = new ReportDAO();
        Report report = dao.findById(reportID);

        if (report == null || !"PENDING".equals(report.getReportStatus())) {
            response.sendRedirect(request.getContextPath() + "/admin/reports");
            return;
        }

        String status = request.getParameter("status");
        String adminReply = request.getParameter("adminReply");
        report.setReportStatus(status);
        report.setAdminReply(adminReply);
        dao.replyReport(report.getReportID(), report.getAdminReply(), report.getReportStatus());
        response.sendRedirect(request.getContextPath() + "/admin/reports");
    }
}
