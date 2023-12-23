package com.ecostore.controller.admin;

import com.ecostore.model.SlideModel;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.ISlideService;
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

@WebServlet(urlPatterns = "/quan-tri/banner")
public class BannerController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private ISlideService slideService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        String url ="";
        String id = request.getParameter("id");
        if (id != null){
            SlideModel slide = slideService.findOneById(Long.parseLong(id));
            request.setAttribute("slide", slide);
            url = "../views/admin/editslide.jsp";
        } else {
            List<SlideModel> slides = slideService.findAll();
            request.setAttribute("slides", slides);
            url = "../views/admin/slide.jsp";
        }
        MessageUtil.showMessage(request);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
