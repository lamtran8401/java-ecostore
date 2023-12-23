package com.ecostore.mapper;

import com.ecostore.model.ProductGalleryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductGalleryMapper implements IRowMapper<ProductGalleryModel> {

    @Override
    public ProductGalleryModel mapRow(ResultSet resultSet) {
        ProductGalleryModel model = new ProductGalleryModel();
        try {
            model.setId(resultSet.getLong("id"));
            model.setImageLink(resultSet.getString("imagelink"));
            model.setProductId(resultSet.getInt("productid"));
            return model;
        } catch (SQLException throwables) {
            return null;
        }
    }
}
