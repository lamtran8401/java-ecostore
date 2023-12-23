package com.ecostore.dao.impl;

import com.ecostore.dao.IMenuTypeDAO;
import com.ecostore.mapper.MenuTypeMapper;
import com.ecostore.model.MenuTypeModel;

import java.util.List;

public class MenuTypeDAO extends AbstractDAO<MenuTypeModel> implements IMenuTypeDAO {
    @Override
    public List<MenuTypeModel> findAll() {
        String sql = "SELECT * FROM menutype";
        return query(sql, new MenuTypeMapper());
    }

    @Override
    public MenuTypeModel findOneMenuTypeById(long id) {
        String sql = "SELECT * FROM menutype WHERE id = ?";
        List <MenuTypeModel> menuTypeModels = query(sql, new MenuTypeMapper(), id);
        if (menuTypeModels.size() == 0) return null;
        return menuTypeModels.get(0);

    }
}
