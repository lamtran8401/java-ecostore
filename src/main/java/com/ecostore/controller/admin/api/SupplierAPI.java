package com.ecostore.controller.admin.api;

import com.ecostore.model.SupplierModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.ISupplierService;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/api-admin-supplier")
public class SupplierAPI extends HttpServlet {
    @Inject
    private ISupplierService supplierService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        SupplierModel supplierModel = mapper.readValue(request.getInputStream(), SupplierModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        supplierModel.setCreatedBy(userModel.getUsername());
        supplierModel = supplierService.insert(supplierModel);
        mapper.writeValue(response.getOutputStream(), supplierModel);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        SupplierModel supplierModel = mapper.readValue(request.getInputStream(), SupplierModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        supplierModel.setModifiedBy(userModel.getUsername());
        supplierModel = supplierService.update(supplierModel);
        mapper.writeValue(response.getOutputStream(), supplierModel);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        SupplierModel supplierModel = mapper.readValue(request.getInputStream(), SupplierModel.class);
        boolean result = supplierService.delete(supplierModel.getIds());
        mapper.writeValue(response.getOutputStream(), result);
    }
}
