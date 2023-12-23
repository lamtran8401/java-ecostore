package com.ecostore.controller.admin.api;

import com.ecostore.model.TermsModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.ITermsService;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-admin-dieu-khoan")
public class TermsAPI extends HttpServlet {
    @Inject
    private ITermsService termsService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        TermsModel termsModel = mapper.readValue(request.getInputStream(), TermsModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        termsModel.setCreatedBy(userModel.getUsername());
        termsModel = termsService.insert(termsModel);
        mapper.writeValue(response.getOutputStream(), termsModel);


    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        TermsModel termsModel = mapper.readValue(request.getInputStream(), TermsModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        termsModel.setModifiedBy(userModel.getUsername());
        termsModel = termsService.update(termsModel);
        mapper.writeValue(response.getOutputStream(), termsModel);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        TermsModel termsModel = mapper.readValue(request.getInputStream(), TermsModel.class);
        boolean result = termsService.delete(termsModel.getIds());
        mapper.writeValue(response.getOutputStream(), result);
    }
}
