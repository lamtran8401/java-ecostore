package com.ecostore.controller.admin.api;

import com.ecostore.model.MenuModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.IMenuService;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-admin-menu")
public class MenuAPI extends HttpServlet {

    @Inject
    private IMenuService menuService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        MenuModel menuModel = mapper.readValue(request.getInputStream(), MenuModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        menuModel.setCreatedBy(userModel.getUsername());
        menuModel = menuService.insert(menuModel);
        mapper.writeValue(response.getOutputStream(), menuModel);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        MenuModel menuModel = mapper.readValue(request.getInputStream(), MenuModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        menuModel.setModifiedBy(userModel.getUsername());
        menuModel = menuService.update(menuModel);
        mapper.writeValue(response.getOutputStream(), menuModel);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        MenuModel menuModel = mapper.readValue(request.getInputStream(), MenuModel.class);
        boolean result = menuService.delete(menuModel.getIds());
        mapper.writeValue(response.getOutputStream(), result);
    }
}
