package com.ecostore.controller.admin.api;

import com.ecostore.model.PaymentModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.IPaymentService;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-admin-payment")
public class PaymentAPI extends HttpServlet {
    @Inject
    private IPaymentService paymentService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        PaymentModel paymentModel = mapper.readValue(request.getInputStream(), PaymentModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        paymentModel.setCreatedBy(userModel.getUsername());
        paymentModel = paymentService.insert(paymentModel);
        mapper.writeValue(response.getOutputStream(), paymentModel);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        PaymentModel paymentModel = mapper.readValue(request.getInputStream(), PaymentModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        paymentModel.setModifiedBy(userModel.getUsername());
        paymentModel = paymentService.update(paymentModel);
        mapper.writeValue(response.getOutputStream(), paymentModel);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        PaymentModel paymentModel = mapper.readValue(request.getInputStream(), PaymentModel.class);
        boolean result = paymentService.delete(paymentModel.getIds());
        mapper.writeValue(response.getOutputStream(), result);
    }
}
