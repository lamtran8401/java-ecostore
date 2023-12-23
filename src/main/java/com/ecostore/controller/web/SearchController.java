package com.ecostore.controller.web;

import com.ecostore.constant.SystemConstant;
import com.ecostore.model.ProductModel;
import com.ecostore.paging.IPageble;
import com.ecostore.paging.PageRequest;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IProductService;
import com.ecostore.sort.Sorter;
import com.ecostore.utils.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/tim-kiem")
public class SearchController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IProductService productService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setHeaderWeb(request);
        layoutAttributeService.setFooterWeb(request);

        String keyword = request.getParameter("keyword");
        ProductModel model = FormUtil.toModel(ProductModel.class, request);
        if (keyword != null) {
            String page = request.getParameter("page");
            if (page == null) model.setPage(1);
            IPageble pageble = new PageRequest(model.getPage(), SystemConstant.LIMIT_ITEMS_SEARCH, new Sorter(model.getSortName(), model.getSortBy()));
            List<ProductModel> products = productService.findAllByKeyWord(keyword, pageble);
            model.setList(products);
            model.setTotalItems(productService.getTotalItemsByKeyword(keyword));
            model.setTotalPage((int) Math.ceil(model.getTotalItems() * 1.0 / pageble.getLimit()));
            request.setAttribute("model", model);
            request.setAttribute("keyword", keyword);
            RequestDispatcher rd = request.getRequestDispatcher("views/web/search.jsp");
            rd.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath() + "/trang-chu");
    }
}
