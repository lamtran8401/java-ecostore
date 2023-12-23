package com.ecostore.controller.admin;

import com.ecostore.model.OrderDetailsModel;
import com.ecostore.model.OrdersModel;
import com.ecostore.model.PaymentModel;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IOrderDetailService;
import com.ecostore.service.IOrderService;
import com.ecostore.service.IPaymentService;
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

@WebServlet(urlPatterns = "/quan-tri/don-hang")
public class OrdersController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IOrderService orderService;
    @Inject
    private IPaymentService paymentService;
    @Inject
    private IOrderDetailService orderDetailService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        String url = "";
        String idorder = request.getParameter("idorder");
        String idorderdetails = request.getParameter("idorderdetails");
        if (idorder != null) {
            OrdersModel order = orderService.findOneById(Long.parseLong(idorder));
            request.setAttribute("order", order);
            List<PaymentModel> payments = paymentService.findAll();
            request.setAttribute("payments", payments);
            url = "../views/admin/editorder.jsp";
        } else if (idorderdetails != null){
            OrdersModel order = orderService.findOneById(Long.parseLong(idorderdetails));
            request.setAttribute("order", order);
            List<OrderDetailsModel> orderDetails = orderDetailService.findAllByOrderId(Long.parseLong(idorderdetails));
            request.setAttribute("orderDetails", orderDetails);
            url ="../views/admin/orderdetails.jsp";
        } else {
            url = "../views/admin/orders.jsp";
            List<OrdersModel> orders = orderService.findAll();
            request.setAttribute("orders", orders);
        }

        MessageUtil.showMessage(request);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
