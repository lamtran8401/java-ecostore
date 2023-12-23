package com.ecostore.controller.web.api;


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

@WebServlet(urlPatterns = "/api-web-feedback")

public class FeedbackAPI extends HttpServlet {
    @Inject
    private IFeedBackService feedbackService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        if(user == null) {
            mapper.writeValue(response.getOutputStream(), "");
        } else {
            FeedBackModel feedbackModel = mapper.readValue(request.getInputStream(), FeedBackModel.class);
            feedbackModel.setUserId(user.getId());
            feedbackModel.setUser(user);
            feedbackModel.setCreatedBy(user.getFullname());
            feedbackModel.setStatus(0);
            feedbackModel = feedbackService.insert(feedbackModel);
            mapper.writeValue(response.getOutputStream(), feedbackModel);
        }
    }
}
