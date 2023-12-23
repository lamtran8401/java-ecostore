package com.ecostore.controller.web.api;

import com.ecostore.model.CommentModel;
import com.ecostore.model.ContactModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.ICommentService;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-web-comment")
public class CommentAPI extends HttpServlet {

    @Inject
    private ICommentService commentService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        CommentModel comment = mapper.readValue(request.getInputStream(), CommentModel.class);
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        comment.setStatus(1);
        comment.setCreatedBy(user.getUsername());
        comment = commentService.insert(comment);
        mapper.writeValue(response.getOutputStream(), comment);
    }
}
