package com.ecostore.mapper;

import com.ecostore.model.SupplierModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierMapper  implements IRowMapper{
    @Override
    public SupplierModel mapRow(ResultSet resultSet) {
        SupplierModel model = new SupplierModel();
        try {
            model.setId(resultSet.getLong("id"));
            model.setName(resultSet.getString("name"));
            model.setCode(resultSet.getString("code"));
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
