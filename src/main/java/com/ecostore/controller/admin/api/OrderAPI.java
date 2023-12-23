package com.ecostore.controller.admin.api;

import com.ecostore.model.MenuModel;
import com.ecostore.model.OrdersModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.IOrderService;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-admin-order")
public class OrderAPI extends HttpServlet {
    @Inject
    private IOrderService orderService;

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        OrdersModel order = mapper.readValue(request.getInputStream(), OrdersModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        order.setModifiedBy(userModel.getUsername());
        order = orderService.update(order);
        mapper.writeValue(response.getOutputStream(), order);
    }
}
