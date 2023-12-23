package com.ecostore.mapper;

import com.ecostore.model.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements IRowMapper<CategoryModel> {
    @Override
    public CategoryModel mapRow(ResultSet resultSet) {
        CategoryModel model = new CategoryModel();
        try {
            model.setId(resultSet.getLong("id"));
            model.setName(resultSet.getString("name"));
            model.setCode(resultSet.getString("code"));
            model.setStatus(resultSet.getInt("status"));
            if (resultSet.getTimestamp("createddate") != null) {
                model.setCreatedDate(resultSet.getTimestamp("createddate"));
            }
            if (resultSet.getString("createdby") != null) {
                model.setCreatedBy(resultSet.getString("createdby"));
            }
            if (resultSet.getTimestamp("modifieddate") != null) {
                model.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            }
            if (resultSet.getString("modifiedby") != null) {
                model.setModifiedBy(resultSet.getString("modifiedby"));
            }
            return model;
        } catch (SQLException throwables) {
            return null;
        }

    }
}
