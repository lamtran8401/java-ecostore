package com.ecostore.mapper;

import com.ecostore.model.MenuTypeModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuTypeMapper implements IRowMapper<MenuTypeModel> {
    @Override
    public MenuTypeModel mapRow(ResultSet resultSet) {
        MenuTypeModel model = new MenuTypeModel();
        try {
            model.setId(resultSet.getLong("id"));
            model.setName(resultSet.getString("name"));
            return model;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
}
