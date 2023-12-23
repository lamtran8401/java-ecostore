package com.ecostore.mapper;

import com.ecostore.model.PrivacyPolicyModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrivacyPolicyMapper implements IRowMapper<PrivacyPolicyModel> {

    @Override
    public PrivacyPolicyModel mapRow(ResultSet resultSet) {
        PrivacyPolicyModel model = new PrivacyPolicyModel();
        try {
            model.setId(resultSet.getLong("id"));
            model.setContent(resultSet.getString("content"));
            model.setStatus(resultSet.getInt("status"));

            if (resultSet.getTimestamp("createddate") != null)
                model.setCreatedDate(resultSet.getTimestamp("createddate"));
            if (resultSet.getString("createdby") != null)
                model.setCreatedBy(resultSet.getString("createdby"));
            if (resultSet.getTimestamp("modifieddate") != null)
                model.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            if (resultSet.getString("modifiedby") != null)
                model.setCreatedBy(resultSet.getString("modifiedby"));
            return model;
        } catch (SQLException throwables) {
            return null;
        }
    }
}
