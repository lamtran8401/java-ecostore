package com.ecostore.controller.web.api;

import com.ecostore.model.ContactModel;
import com.ecostore.service.IContactService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-web-contact")

public class ContactAPI extends HttpServlet {
    @Inject
    private IContactService contactService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        ContactModel contactModel = mapper.readValue(request.getInputStream(), ContactModel.class);
        contactModel.setStatus(0);
        contactModel = contactService.insert(contactModel);
        mapper.writeValue(response.getOutputStream(), contactModel);
    }
}
