package com.ecostore.service.impl;

import com.ecostore.dao.IMenuTypeDAO;
import com.ecostore.model.MenuTypeModel;
import com.ecostore.service.IMenuTypeService;

import javax.inject.Inject;
import java.util.List;

public class MenuTypeService implements IMenuTypeService {
    @Inject
    private IMenuTypeDAO menuTypeDAO;
    @Override
    public List<MenuTypeModel> findAll() {
        return menuTypeDAO.findAll();
    }

    @Override
    public MenuTypeModel findOneMenuTypeById(long id) {
        return menuTypeDAO.findOneMenuTypeById(id);
    }
}
