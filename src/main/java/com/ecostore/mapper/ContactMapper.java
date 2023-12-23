package com.ecostore.mapper;


import com.ecostore.model.ContactModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper implements IRowMapper<ContactModel> {
    @Override
    public ContactModel mapRow(ResultSet resultSet) {
        ContactModel model = new ContactModel();
        try {
            model.setId(resultSet.getLong("id"));
            model.setFullname(resultSet.getString("fullname"));
            model.setEmail(resultSet.getString("email"));
            model.setContent(resultSet.getString("content"));
            model.setStatus(resultSet.getInt("status"));
            model.setCreatedDate(resultSet.getTimestamp("createddate"));
            model.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            model.setModifiedBy(resultSet.getString("modifiedby"));
            return model;
        } catch (SQLException throwables) {
            return null;
        }

    }
}
