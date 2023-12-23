package com.ecostore.controller.admin;

import com.ecostore.constant.SystemConstant;
import com.ecostore.model.UserModel;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IOrderService;
import com.ecostore.service.IProductService;
import com.ecostore.service.IUserService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/quan-tri/trang-chu")
public class HomeController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IUserService userService;
    @Inject
    private IProductService productService;
    @Inject
    private IOrderService orderService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        List<UserModel> admins = userService.findAllUsersByRole(SystemConstant.ADMIN);
        int totalUser = userService.getTotalUserByRole(SystemConstant.USER);
        int totalProductActive = productService.getTotalItemsByStatus(1);
        int totalOrders = orderService.getTotalOrders();
        long revenue = orderService.getRevenue();

        request.setAttribute("totalUsers",totalUser);
        request.setAttribute("totalOrders",totalOrders);
        request.setAttribute("totalProductActive",totalProductActive);
        request.setAttribute("admins",admins);
        request.setAttribute("revenue",revenue);
        RequestDispatcher rd = request.getRequestDispatcher("../views/admin/home.jsp");
        rd.forward(request, response);
    }
}
