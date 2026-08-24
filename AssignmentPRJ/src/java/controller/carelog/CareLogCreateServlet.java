/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.carelog;

import dal.CareLogDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CareLog;
import model.Plant;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "CareLogCreateServlet", urlPatterns = {"/carelogs/create"})
public class CareLogCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String plantID = request.getParameter("plantID");
        if (plantID == null || plantID.trim().isEmpty()) {
            plantID = request.getParameter("plantId");
        }
        if (plantID != null && !plantID.trim().isEmpty()) {
            request.setAttribute("plantID", Integer.parseInt(plantID));
        }
        request.setAttribute("today", new java.sql.Date(System.currentTimeMillis()));
        request.getRequestDispatcher("/carelog/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String plantIDParam = request.getParameter("plantID");
        if (plantIDParam == null || plantIDParam.trim().isEmpty()) {
            plantIDParam = request.getParameter("plantId");
        }

        String actionType = request.getParameter("actionType");
        String description = request.getParameter("description");

        if (plantIDParam == null || plantIDParam.trim().isEmpty() || actionType == null || actionType.trim().isEmpty()) {
            request.setAttribute("error", "Plant and action type are required.");
            request.setAttribute("plantID", plantIDParam == null ? null : Integer.parseInt(plantIDParam));
            request.getRequestDispatcher("/carelog/form.jsp").forward(request, response);
            return;
        }

        int plantID = Integer.parseInt(plantIDParam);
        CareLog log = new CareLog();
        Plant plant = new Plant();
        plant.setPlantID(plantID);
        log.setPlant(plant);
        log.setActionType(actionType.trim());
        log.setDescription(description == null ? "" : description.trim());
        log.setActionDate(new java.sql.Date(System.currentTimeMillis()));

        new CareLogDAO().insert(log);
        response.sendRedirect(request.getContextPath() + "/plants/detail?id=" + plantID);
    }
}
