package com.ecostore.controller.admin;

import com.ecostore.model.SupplierModel;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.ISupplierService;
import com.ecostore.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/quan-tri/nha-san-xuat")
public class SupplierController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private ISupplierService supplierService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        String url = "";
        String id = request.getParameter("id");
        if (id != null) {
            SupplierModel supplierModel = supplierService.findOneById(Long.parseLong(id));
            request.setAttribute("supplierModel", supplierModel);
            url = "../views/admin/editsupplier.jsp";
        } else {
            List<SupplierModel> suppliers = supplierService.findAll();
            request.setAttribute("suppliers", suppliers);
            url = "../views/admin/supplier.jsp";
        }
        MessageUtil.showMessage(request);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
