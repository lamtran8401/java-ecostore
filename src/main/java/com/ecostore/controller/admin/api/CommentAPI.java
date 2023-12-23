package com.ecostore.controller.admin.api;

import com.ecostore.model.CommentModel;
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

@WebServlet(urlPatterns = "/api-admin-comment")
public class CommentAPI extends HttpServlet {
    @Inject
    private ICommentService commentService;

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        CommentModel commentModel = mapper.readValue(request.getInputStream(), CommentModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        commentModel.setModifiedBy(userModel.getFullname());
        commentModel = commentService.update(commentModel);
        mapper.writeValue(response.getOutputStream(), commentModel);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        CommentModel commentModel = mapper.readValue(request.getInputStream(), CommentModel.class);
        boolean result = commentService.delete(commentModel.getIds());
        mapper.writeValue(response.getOutputStream(), result);
    }
}
