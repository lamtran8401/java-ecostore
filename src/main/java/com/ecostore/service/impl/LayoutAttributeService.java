package com.ecostore.service.impl;

import com.ecostore.model.CategoryModel;
import com.ecostore.model.InformationModel;
import com.ecostore.model.MenuModel;
import com.ecostore.service.ICategoryService;
import com.ecostore.service.IInformationService;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IMenuService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LayoutAttributeService implements ILayoutAttributeService  {
    @Inject
    private IMenuService menuService;
    @Inject
    private ICategoryService categoryService;
    @Inject
    private IInformationService informationService;

    @Override
    public void setHeaderWeb(HttpServletRequest request) {
        List<MenuModel> menuTop = menuService.findAllByMenuTypeId(1, 1);
        request.setAttribute("menuTop", menuTop);
        List<CategoryModel> categories = categoryService.findAllByStatus(1);
        request.setAttribute("categories", categories);
    }

    @Override
    public void setFooterWeb(HttpServletRequest request) {
        List<MenuModel> menuBottom = menuService.findAllByMenuTypeId(2, 1);
        request.setAttribute("menuBottom", menuBottom);
        InformationModel information = informationService.findOneByStatus(1);
        request.setAttribute("information", information);
    }

    @Override
    public void setMenuLeftAdmin(HttpServletRequest request) {
        List<MenuModel> menuLeft = menuService.findAllByMenuTypeId(3, 1);
        request.setAttribute("menuLeft", menuLeft);
    }
}
