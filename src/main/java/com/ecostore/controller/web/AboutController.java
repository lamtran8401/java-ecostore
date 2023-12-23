package com.ecostore.controller.web;

import com.ecostore.model.AboutModel;
import com.ecostore.service.*;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/gioi-thieu")
public class AboutController extends HttpServlet {
    @Inject
    private IAboutService aboutService;
    @Inject
    private ILayoutAttributeService layoutAttributeService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setHeaderWeb(request);
        layoutAttributeService.setFooterWeb(request);
        AboutModel model = aboutService.findOneByStatus(1);
        request.setAttribute("model", model);
        RequestDispatcher rd = request.getRequestDispatcher("views/web/about.jsp");
        rd.forward(request, response);
    }
}
