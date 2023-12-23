package com.ecostore.controller.web;

import com.ecostore.model.CommentModel;
import com.ecostore.model.ProductModel;
import com.ecostore.service.ICommentService;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IProductService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/chi-tiet-san-pham")
public class ProductDetailController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IProductService productService;
    @Inject
    private ICommentService commentService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        layoutAttributeService.setHeaderWeb(request);
        layoutAttributeService.setFooterWeb(request);
        String id = request.getParameter("id");

        if (id != null) {
            ProductModel product = productService.findOneById(Long.parseLong(id));
            List<ProductModel> products = productService.findAllByCategoryId(product.getCategoryId());
            product.setList(products);
            request.setAttribute("product", product);

            List<CommentModel> comments = commentService.findAllByProductId(product.getId());
            request.setAttribute("comments", comments);

            RequestDispatcher rd = request.getRequestDispatcher("views/web/single.jsp");
            rd.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath() + "/trang-chu");
    }
}
