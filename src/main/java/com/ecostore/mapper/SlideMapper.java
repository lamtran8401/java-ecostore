package com.ecostore.mapper;

import com.ecostore.model.SlideModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SlideMapper implements IRowMapper<SlideModel> {
    @Override
    public SlideModel mapRow(ResultSet resultSet) {
        SlideModel model = new SlideModel();
        try{
            model.setId(resultSet.getLong("id"));
            model.setTitle(resultSet.getString("title"));
            model.setDescription(resultSet.getString("description"));
            model.setImageLink(resultSet.getString("imagelink"));
            model.setDisplayorder(resultSet.getInt("displayorder"));
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
