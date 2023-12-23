package com.ecostore.controller.admin;

import com.ecostore.model.InformationModel;
import com.ecostore.service.IInformationService;
import com.ecostore.service.ILayoutAttributeService;
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

@WebServlet(urlPatterns = "/quan-tri/thong-tin")
public class InformationController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IInformationService informationService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        String url = "";
        String id = request.getParameter("id");
        MessageUtil.showMessage(request);
        if (id != null) {
            InformationModel informationModel = informationService.findOneById(Long.parseLong(id));
            request.setAttribute("informationModel", informationModel);
            url = "../views/admin/editinformation.jsp";
        } else {
            List<InformationModel> information = informationService.findAll();
            request.setAttribute("information", information);
            url = "../views/admin/information.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
