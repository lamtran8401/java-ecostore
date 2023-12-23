package com.ecostore.mapper;

import com.ecostore.model.CommentModel;
import com.ecostore.model.ProductModel;
import com.ecostore.model.UserModel;
import com.ecostore.utils.MapperUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements IRowMapper<CommentModel> {

    @Override
    public CommentModel mapRow(ResultSet resultSet) {
        CommentModel model = new CommentModel();
        try {
            model.setId(resultSet.getLong("id"));
            model.setUserId(resultSet.getLong("userid"));
            model.setProductId(resultSet.getLong("productid"));
            model.setContent((resultSet.getString("content")));
            model.setStatus(resultSet.getInt("status"));
            UserModel user = new UserModel();
            if (MapperUtil.hasColumn(resultSet, "fullname")) {
                user.setFullname(resultSet.getString("fullname"));
            }
            if (MapperUtil.hasColumn(resultSet, "avatar")) {
                user.setAvatar(resultSet.getString("avatar"));
            }
            ProductModel product = new ProductModel();
            if (MapperUtil.hasColumn(resultSet, "name")) {
                product.setName(resultSet.getString("name"));
            }
            model.setUser(user);
            model.setProduct(product);
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
