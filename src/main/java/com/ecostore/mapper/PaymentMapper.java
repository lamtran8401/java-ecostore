package com.ecostore.mapper;

import com.ecostore.model.PaymentModel;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements IRowMapper<PaymentModel> {
    @Override
    public PaymentModel mapRow(ResultSet resultSet) {
        PaymentModel model = new PaymentModel();
        try {
            model.setId(resultSet.getLong("id"));
            model.setName(resultSet.getString("name"));
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
