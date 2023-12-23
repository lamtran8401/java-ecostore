package com.ecostore.mapper;

import com.ecostore.model.TermsModel;

import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class TermsMapper implements  IRowMapper{


    @Override
    public TermsModel mapRow(ResultSet resultSet) {
        TermsModel model = new TermsModel();
        try{
            model.setId(resultSet.getLong("id"));
            model.setContent(resultSet.getString("content"));
            model.setStatus(resultSet.getInt("status"));
            model.setCreatedDate((resultSet.getTimestamp("createddate")));
            model.setCreatedBy((resultSet.getString("createdby")));
            model.setModifiedDate((resultSet.getTimestamp("modifieddate")));
            model.setModifiedBy((resultSet.getString("modifiedby")));
            return model;

        }catch (SQLException throwables){
                return null;
        }

    }
}
