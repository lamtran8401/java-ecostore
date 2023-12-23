package com.ecostore.controller.admin;

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

@WebServlet(urlPatterns = "/quan-tri/phan-hoi")
public class FeedBackController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IFeedBackService feedbackService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        String url = "";
        String id = request.getParameter("id");
        MessageUtil.showMessage(request);
        if (id != null){
            FeedBackModel feedbackModel = feedbackService.findOneById(Long.parseLong(id));
            request.setAttribute("feedbackModel" , feedbackModel);
            url= "../views/admin/editfeedback.jsp";
        } else{
            List<FeedBackModel> feedbacks = feedbackService.findAll();
            request.setAttribute("feedbacks" , feedbacks);
            url= "../views/admin/feedback.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request,response);
    }
}
