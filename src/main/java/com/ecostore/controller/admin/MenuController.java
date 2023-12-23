package com.ecostore.controller.admin;

import com.ecostore.model.MenuModel;
import com.ecostore.model.MenuTypeModel;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IMenuService;
import com.ecostore.service.IMenuTypeService;
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

@WebServlet(urlPatterns = "/quan-tri/menu")
public class MenuController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IMenuTypeService menuTypeService;
    @Inject
    private IMenuService menuService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        String url = "";
        String id = request.getParameter("id");
        MessageUtil.showMessage(request);
        if (id != null) {
            MenuModel menuModel = menuService.findOneById(Long.parseLong(id));
            request.setAttribute("menuModel", menuModel);
            List<MenuTypeModel> menuTypeModels = menuTypeService.findAll();
            request.setAttribute("menuTypeModels", menuTypeModels);
            url = "../views/admin/editmenu.jsp";
        } else {
            List<MenuModel> menus = menuService.findAll();
            request.setAttribute("menus", menus);
            List<MenuTypeModel> menuTypeModels = menuTypeService.findAll();
            request.setAttribute("menuTypeModels", menuTypeModels);
            url = "../views/admin/menu.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
