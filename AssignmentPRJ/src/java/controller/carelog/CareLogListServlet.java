/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.carelog;

import dal.CareLogDAO;
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
@WebServlet(name = "CareLogListServlet", urlPatterns = {"/carelogs"})
public class CareLogListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String plantID = request.getParameter("plantID");
        List<CareLog> careLogs;

        if (plantID != null && !plantID.trim().isEmpty()) {
            careLogs = new CareLogDAO().findByPlantId(Integer.parseInt(plantID));
        } else {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("account") != null) {
                User account = (User) session.getAttribute("account");
                careLogs = new CareLogDAO().findByUserId(account.getUserID());
            } else {
                careLogs = java.util.Collections.emptyList();
            }
        }

        request.setAttribute("careLogs", careLogs);
        request.getRequestDispatcher("/carelog/list.jsp").forward(request, response);
    }
}
