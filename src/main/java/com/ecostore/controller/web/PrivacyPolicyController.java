package com.ecostore.controller.web;

import com.ecostore.model.PrivacyPolicyModel;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IPrivacyPolicyService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/chinh-sach-bao-mat")
public class PrivacyPolicyController extends HttpServlet {
    @Inject
    private IPrivacyPolicyService privacyPolicyService;
    @Inject
    private ILayoutAttributeService layoutAttributeService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setHeaderWeb(request);
        layoutAttributeService.setFooterWeb(request);
        PrivacyPolicyModel model = privacyPolicyService.findOneByStatus(1);
        request.setAttribute("model", model);
        RequestDispatcher rd = request.getRequestDispatcher("views/web/privacy.jsp");
        rd.forward(request, response);
    }
}
