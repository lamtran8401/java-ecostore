package com.ecostore.controller.admin.api;


import com.ecostore.model.ContactModel;
import com.ecostore.model.UserModel;

import com.ecostore.service.IContactService;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-admin-contact")
public class ContactAPI extends HttpServlet {

    @Inject
    private IContactService contactService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        ContactModel contactModel = mapper.readValue(request.getInputStream(), ContactModel.class);
        contactModel = contactService.insert(contactModel);
        mapper.writeValue(response.getOutputStream(), contactModel);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        ContactModel contactModel = mapper.readValue(request.getInputStream(), ContactModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        contactModel.setModifiedBy(userModel.getUsername());
        contactModel = contactService.update(contactModel);
        mapper.writeValue(response.getOutputStream(), contactModel);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        ContactModel contactModel = mapper.readValue(request.getInputStream(), ContactModel.class);
        boolean result = contactService.delete(contactModel.getIds());
        mapper.writeValue(response.getOutputStream(), result);
    }
}
