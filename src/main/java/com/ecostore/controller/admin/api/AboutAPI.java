package com.ecostore.controller.admin.api;

import com.ecostore.model.AboutModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.IAboutService;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-admin-about")
public class AboutAPI extends HttpServlet {

    @Inject
    private IAboutService aboutService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        AboutModel aboutModel = mapper.readValue(request.getInputStream(), AboutModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        aboutModel.setCreatedBy(userModel.getUsername());
        aboutModel = aboutService.insert(aboutModel);
        mapper.writeValue(response.getOutputStream(), aboutModel);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        AboutModel aboutModel = mapper.readValue(request.getInputStream(), AboutModel.class);
        boolean result = aboutService.delete(aboutModel.getIds());
        mapper.writeValue(response.getOutputStream(), result);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        AboutModel aboutModel = mapper.readValue(request.getInputStream(), AboutModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        aboutModel.setModifiedBy(userModel.getUsername());
        aboutModel = aboutService.update(aboutModel);
        mapper.writeValue(response.getOutputStream(), aboutModel);
    }
}
