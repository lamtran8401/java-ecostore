package com.ecostore.controller.web.api;

import com.ecostore.model.*;
import com.ecostore.service.IOrderDetailService;
import com.ecostore.service.IOrderService;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/api-web-order")
public class OrderAPI extends HttpServlet {
    @Inject
    private IOrderService orderService;
    @Inject
    private IOrderDetailService orderDetailService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        OrdersModel ordersModel = mapper.readValue(request.getInputStream(), OrdersModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        Cart cart = (Cart) SessionUtil.getInstance().getValue(request, "CART");
        ordersModel.setCreatedBy(userModel.getUsername());
        ordersModel.setTotalPrice(cart.total());
        ordersModel.setStatus(0);
        ordersModel = orderService.insert(ordersModel);
        long orderId = ordersModel.getId();

        for (CartItem item : cart.data()) {
            OrderDetailsModel orderDetails = new OrderDetailsModel();
            orderDetails.setOrderId(orderId);
            orderDetails.setProductId(item.getProductId());
            orderDetails.setUnitPrice(item.getUnitPrice());
            orderDetails.setQuantity(item.getQuantity());
            orderDetails.setTotalPrice(item.getUnitPrice() * item.getQuantity());
            orderDetailService.insert(orderDetails);
        }
        if(ordersModel != null)
            SessionUtil.getInstance().removeValue(request, "CART");
        mapper.writeValue(response.getOutputStream(), ordersModel);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        OrdersModel order = mapper.readValue(request.getInputStream(), OrdersModel.class);
        order = orderService.findOneById(order.getId());
        order.setModifiedBy(userModel.getUsername());
        order.setStatus(4);
        order = orderService.update(order);
//        List<OrderDetailsModel> orderDetails = orderDetailService.findAllByOrderId(order.getId());
//        for (OrderDetailsModel item : orderDetails) {
//            orderDetailService.delete(item.getId());
//        }
        mapper.writeValue(response.getOutputStream(), order);

    }
}
