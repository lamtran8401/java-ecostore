package com.ecostore.mapper;

import com.ecostore.model.FeedBackModel;
import com.ecostore.model.UserModel;
import com.ecostore.utils.MapperUtil;


import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedBackMapper implements IRowMapper<FeedBackModel> {
    @Override
    public FeedBackModel mapRow(ResultSet resultSet) {
        FeedBackModel model = new FeedBackModel();
        try {

            model.setId(resultSet.getLong("id"));
            model.setUserId(resultSet.getLong("userid"));
            model.setContent(resultSet.getString("content"));
            model.setStatus(resultSet.getInt("status"));
            UserModel usermodel = new UserModel();
            if (MapperUtil.hasColumn(resultSet, "fullname")) {
                usermodel.setFullname(resultSet.getString("fullname"));
            }
            if (MapperUtil.hasColumn(resultSet, "avatar")) {
                usermodel.setAvatar(resultSet.getString("avatar"));
            }
            model.setUser(usermodel);
            model.setCreatedDate(resultSet.getTimestamp("createddate"));
            model.setCreatedBy(resultSet.getString("createdby"));
            model.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            model.setModifiedBy(resultSet.getString("createdby"));
            return model;
        } catch (SQLException throwables) {
            return null;
        }
    }
}
