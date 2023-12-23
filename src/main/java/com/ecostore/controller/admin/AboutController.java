package com.ecostore.controller.admin;

import com.ecostore.model.AboutModel;
import com.ecostore.service.IAboutService;
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

@WebServlet(urlPatterns = "/quan-tri/gioi-thieu")
public class AboutController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IAboutService aboutService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        String url = "";
        String id = request.getParameter("id");
        MessageUtil.showMessage(request);
        if (id != null) {
            AboutModel aboutModel = aboutService.findOneById(Long.parseLong(id));
            request.setAttribute("aboutModel", aboutModel);
            url = "../views/admin/editabout.jsp";
        } else {
            List<AboutModel> abouts = aboutService.findAll();
            request.setAttribute("abouts", abouts);
            url = "../views/admin/abouts.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
