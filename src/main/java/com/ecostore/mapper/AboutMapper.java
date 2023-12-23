package com.ecostore.mapper;

import com.ecostore.model.AboutModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AboutMapper implements IRowMapper<AboutModel> {

    @Override
    public AboutModel mapRow(ResultSet resultSet) {
        AboutModel model = new AboutModel();
        try {
            model.setId(resultSet.getLong("id"));
            model.setContent(resultSet.getString("content"));
            model.setStatus(resultSet.getInt("status"));
            model.setCreatedDate(resultSet.getTimestamp("createddate"));
            model.setCreatedBy(resultSet.getString("createdby"));
            model.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            model.setModifiedBy(resultSet.getString("modifiedby"));
            return model;
        } catch (SQLException throwables) {
            return null;
        }
    }
}
