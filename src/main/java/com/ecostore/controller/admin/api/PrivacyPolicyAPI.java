package com.ecostore.controller.admin.api;

import com.ecostore.model.PrivacyPolicyModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.IPrivacyPolicyService;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-admin-privacy-policy")
public class PrivacyPolicyAPI extends HttpServlet {
    @Inject
    private IPrivacyPolicyService privacyPolicyService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        PrivacyPolicyModel privacyPolicyModel = mapper.readValue(request.getInputStream(), PrivacyPolicyModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        privacyPolicyModel.setCreatedBy(userModel.getUsername());
        privacyPolicyModel = privacyPolicyService.insert(privacyPolicyModel);
        mapper.writeValue(response.getOutputStream(), privacyPolicyModel);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        PrivacyPolicyModel privacyPolicyModel = mapper.readValue(request.getInputStream(), PrivacyPolicyModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        privacyPolicyModel.setModifiedBy(userModel.getUsername());
        privacyPolicyModel = privacyPolicyService.update(privacyPolicyModel);
        mapper.writeValue(response.getOutputStream(), privacyPolicyModel);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        PrivacyPolicyModel privacyPolicyModel = mapper.readValue(request.getInputStream(), PrivacyPolicyModel.class);
        boolean result = privacyPolicyService.delete(privacyPolicyModel.getIds());
        mapper.writeValue(response.getOutputStream(), result);
    }
}
