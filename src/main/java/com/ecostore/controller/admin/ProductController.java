package com.ecostore.controller.admin;

import com.ecostore.model.AboutModel;
import com.ecostore.model.CategoryModel;
import com.ecostore.model.ProductModel;
import com.ecostore.model.SupplierModel;
import com.ecostore.service.ICategoryService;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IProductService;
import com.ecostore.service.ISupplierService;
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
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/quan-tri/san-pham")
public class ProductController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IProductService productService;
    @Inject
    private ICategoryService categoryService;
    @Inject
    private ISupplierService supplierService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        String url = "";
        String id = request.getParameter("id");
        MessageUtil.showMessage(request);
        List<CategoryModel> categories = categoryService.findAll();
        List<SupplierModel> supplier = supplierService.findAll();
        request.setAttribute("categories", categories);
        request.setAttribute("suppliers", supplier);
        if (id != null) {
            ProductModel product = productService.findOneById(Long.parseLong(id));
            request.setAttribute("product", product);
            url = "../views/admin/editproduct.jsp";
        } else {
            List<ProductModel> products = productService.findAll();
            request.setAttribute("products", products);

            url = "../views/admin/products.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
