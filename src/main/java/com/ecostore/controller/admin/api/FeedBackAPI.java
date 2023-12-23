package com.ecostore.controller.admin.api;

import com.ecostore.model.FeedBackModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.IFeedBackService;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-admin-feedback")
public class FeedBackAPI extends HttpServlet {
    @Inject
    private IFeedBackService feedbackService;

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        FeedBackModel feedbackModel = mapper.readValue(request.getInputStream(), FeedBackModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        feedbackModel.setModifiedBy(userModel.getFullname());
        feedbackModel = feedbackService.update(feedbackModel);
        mapper.writeValue(response.getOutputStream(), feedbackModel);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        FeedBackModel feedBackModel = mapper.readValue(request.getInputStream(), FeedBackModel.class);
        boolean result = feedbackService.delete(feedBackModel.getIds());
        mapper.writeValue(response.getOutputStream(), result);
    }
}
