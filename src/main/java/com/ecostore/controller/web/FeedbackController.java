package com.ecostore.controller.web;

import com.ecostore.model.FeedBackModel;
import com.ecostore.service.IFeedBackService;
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

@WebServlet(urlPatterns = "/danh-gia")
public class FeedbackController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IFeedBackService feedBackService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setHeaderWeb(request);
        layoutAttributeService.setFooterWeb(request);
        MessageUtil.showMessage(request);
        List<FeedBackModel> feedbacks = feedBackService.findAllByStatus(1);
        request.setAttribute("feedbacks", feedbacks);
        RequestDispatcher rd = request.getRequestDispatcher("views/web/feedback.jsp");
        rd.forward(request, response);
    }
}
