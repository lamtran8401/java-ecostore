package com.ecostore.mapper;

import com.ecostore.model.MenuModel;
import com.ecostore.model.MenuTypeModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuMapper implements IRowMapper<MenuModel> {

    @Override
    public MenuModel mapRow(ResultSet resultSet) {
        MenuModel model = new MenuModel();
        try {
            model.setId(resultSet.getLong("id"));
            model.setName(resultSet.getString("name"));
            model.setLink(resultSet.getString("link"));
            model.setStatus(resultSet.getInt("status"));
            MenuTypeModel menuTypeModel = new MenuTypeModel();
            model.setMenuTypeId(resultSet.getLong("menutypeid"));
            menuTypeModel.setName(resultSet.getString("menutypename"));
            model.setMenuType(menuTypeModel);
            model.setCreatedDate((resultSet.getTimestamp("createddate")));
            model.setCreatedBy((resultSet.getString("createdby")));
            model.setModifiedDate((resultSet.getTimestamp("modifieddate")));
            model.setModifiedBy((resultSet.getString("modifiedby")));
            return model;
        } catch (SQLException throwables) {

        }
        return null;
    }
}
