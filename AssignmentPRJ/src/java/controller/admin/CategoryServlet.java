/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CategoryDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/admin/categories"})
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            try {
                int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                new CategoryDAO().delete(categoryID);
                response.sendRedirect(request.getContextPath() + "/admin/categories");
                return;
            } catch (Exception e) {
                request.setAttribute("error", "Unable to delete category.");
            }
        }

        List<Category> categories = new CategoryDAO().findAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/admin/category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        CategoryDAO categoryDAO = new CategoryDAO();

        if ("create".equals(action)) {
            String categoryName = request.getParameter("categoryName");
            if (categoryName != null && !categoryName.trim().isEmpty()) {
                Category category = new Category();
                category.setCategoryName(categoryName.trim());
                categoryDAO.insert(category);
                response.sendRedirect(request.getContextPath() + "/admin/categories?added=1");
                return;
            }
            request.setAttribute("error", "Category name is required.");
        } else if ("delete".equals(action)) {
            try {
                int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                categoryDAO.delete(categoryID);
                response.sendRedirect(request.getContextPath() + "/admin/categories");
                return;
            } catch (Exception e) {
                request.setAttribute("error", "Unable to delete category.");
            }
        }

        request.setAttribute("categories", categoryDAO.findAll());
        request.getRequestDispatcher("/admin/category.jsp").forward(request, response);
    }
}
