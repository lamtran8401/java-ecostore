package com.ecostore.controller.admin;

import com.ecostore.model.CategoryModel;
import com.ecostore.service.ICategoryService;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/quan-tri/the-loai")
public class CategoryController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private ICategoryService categoryService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        String url = "";
        String id = request.getParameter("id");
        MessageUtil.showMessage(request);
        if (id != null) {
            CategoryModel categoryModel = categoryService.findOneById(Long.parseLong(id));
            request.setAttribute("categoryModel", categoryModel);
            url = "../views/admin/editcategory.jsp";
        } else {
            List<CategoryModel> categories = categoryService.findAll();
            request.setAttribute("categories", categories);
            url = "../views/admin/categories.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
