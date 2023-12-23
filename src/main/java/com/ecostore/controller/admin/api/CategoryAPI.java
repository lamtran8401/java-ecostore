package com.ecostore.controller.admin.api;

import com.ecostore.model.CategoryModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.ICategoryService;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-admin-category")
public class CategoryAPI extends HttpServlet {

    @Inject
    private ICategoryService categoryService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        CategoryModel categoryModel = mapper.readValue(request.getInputStream(), CategoryModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        categoryModel.setCreatedBy(userModel.getUsername());
        categoryModel = categoryService.insert(categoryModel);
        mapper.writeValue(response.getOutputStream(), categoryModel);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        CategoryModel categoryModel = mapper.readValue(request.getInputStream(), CategoryModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        categoryModel.setModifiedBy(userModel.getUsername());
        categoryModel = categoryService.update(categoryModel);
        mapper.writeValue(response.getOutputStream(), categoryModel);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        CategoryModel categoryModel = mapper.readValue(request.getInputStream(), CategoryModel.class);
        boolean result = categoryService.delete(categoryModel.getIds());
        mapper.writeValue(response.getOutputStream(), result);
    }
}
