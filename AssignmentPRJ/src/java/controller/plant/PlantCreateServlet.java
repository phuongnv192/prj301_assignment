/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.plant;

import dal.CategoryDAO;
import dal.PlantDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Category;
import model.Plant;
import model.User;

@WebServlet(name = "PlantCreateServlet", urlPatterns = {"/plants/create"})
public class PlantCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("mode", "create");
        request.setAttribute("plant", new Plant());
        request.setAttribute("categories", new CategoryDAO().findAll());
        request.getRequestDispatcher("/plant/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User account = (User) session.getAttribute("account");

        Plant plant = new Plant();
        plant.setPlantName(request.getParameter("plantName"));
        Category category = new Category();
        category.setCategoryID(Integer.parseInt(request.getParameter("categoryID")));
        plant.setCategory(category);
        plant.setOwner(account);
        plant.setImageUrl(request.getParameter("imageUrl"));
        plant.setHealthStatus(request.getParameter("healthStatus"));
        plant.setNote(request.getParameter("note"));

        new PlantDAO().insert(plant);
        response.sendRedirect(request.getContextPath() + "/plants");
    }
}
