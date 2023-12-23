package com.ecostore.controller.web;

import com.ecostore.model.PaymentModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IPaymentService;
import com.ecostore.utils.MessageUtil;
import com.ecostore.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/thanh-toan")
public class CheckoutController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IPaymentService paymentService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setHeaderWeb(request);
        layoutAttributeService.setFooterWeb(request);
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        if (user == null)
            response.sendRedirect(request.getContextPath() + "/dang-nhap?message=not_login&alert=danger");
        else {
            MessageUtil.showMessage(request);
            List<PaymentModel> payments = paymentService.findAllByStatus(1);
            request.setAttribute("payments", payments);
            RequestDispatcher rd = request.getRequestDispatcher("views/web/checkout.jsp");
            rd.forward(request, response);
        }
    }
}
