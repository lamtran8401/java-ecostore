package com.ecostore.controller.admin;

import com.ecostore.model.TermsModel;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.ITermsService;
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

@WebServlet(urlPatterns = "/quan-tri/dieu-khoan")
public class TermsController extends HttpServlet {

    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private ITermsService termsService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        String id = request.getParameter("id");
        String url = "";
        if (id != null) {
            TermsModel termsModel = termsService.findOneById(Long.parseLong(id));
            request.setAttribute("termsModel", termsModel);
            url = "../views/admin/editterms.jsp";
        } else {
            List<TermsModel> termsModels = termsService.findAll();
            request.setAttribute("termsModels", termsModels);
            url = "../views/admin/terms.jsp";
        }
        MessageUtil.showMessage(request);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
