package com.ecostore.service.impl;

import com.ecostore.dao.IMenuDAO;
import com.ecostore.dao.IMenuTypeDAO;
import com.ecostore.model.MenuModel;
import com.ecostore.model.MenuTypeModel;
import com.ecostore.service.IMenuService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class MenuService implements IMenuService {

    @Inject
    private IMenuDAO menuDAO;

    @Override
    public List<MenuModel> findAllByMenuTypeId(long menuTypeId, int status) {
        return menuDAO.findAllByMenuTypeId(menuTypeId, status);
    }

    @Override
    public List<MenuModel> findAll() {
        return menuDAO.findAll();
    }

    @Override
    public MenuModel findOneById(long id) {
        return menuDAO.findOneById(id);
    }

    @Override
    public MenuModel insert(MenuModel model) {
        model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Long id = menuDAO.insert(model);
        return menuDAO.findOneById(id);
    }

    @Override
    public MenuModel update(MenuModel model) {
        MenuModel oldMenu = menuDAO.findOneById(model.getId());
        model.setCreatedDate(oldMenu.getCreatedDate());
        model.setCreatedBy(oldMenu.getCreatedBy());
        model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if (menuDAO.update(model))
            return menuDAO.findOneById(model.getId());
        return null;
    }

    @Override
    public boolean delete(long[] ids) {
        for (long id : ids) {
            if (!menuDAO.delete(id))
                return false;
        }
        return true;
    }
}
