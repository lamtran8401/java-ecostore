package com.ecostore.mapper;

import com.ecostore.model.CategoryModel;
import com.ecostore.model.ProductModel;
import com.ecostore.model.SupplierModel;
import com.ecostore.utils.MapperUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductMapper implements IRowMapper<ProductModel> {

    @Override
    public ProductModel mapRow(ResultSet resultSet) {
        ProductModel model = new ProductModel();
        List<String> images = new ArrayList<>();
        try {
            model.setId(resultSet.getLong("id"));
            model.setName(resultSet.getString("name"));
            model.setPrice(resultSet.getLong("price"));
            model.setDiscount(resultSet.getInt("discount"));
            model.setDescription(resultSet.getString("description"));
            model.setStatus(resultSet.getInt("status"));
            model.setCategoryId(resultSet.getLong("categoryid"));
            CategoryModel category = new CategoryModel();
            if (MapperUtil.hasColumn(resultSet, "cname")) {
                category.setName(resultSet.getString("cname"));
            }
            model.setCategory(category);
            model.setSupplierId(resultSet.getLong("supplierid"));
            SupplierModel supplier = new SupplierModel();
            if (MapperUtil.hasColumn(resultSet, "sname")) {
                supplier.setName(resultSet.getString("sname"));
            }
            model.setSupplierModel(supplier);
            model.setCreatedDate(resultSet.getTimestamp("createddate"));
            model.setCreatedBy(resultSet.getString("createdby"));
            model.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            model.setModifiedBy(resultSet.getString("modifiedby"));
            images.add(resultSet.getString("imagelink"));
            boolean checkRs = true;
            while (checkRs = resultSet.next()) {
                if (model.getId().equals(resultSet.getLong("id")))
                    images.add(resultSet.getString("imagelink"));
                else {
                    resultSet.previous();
                    model.setImages(images);
                    break;
                }
            }
            if (!checkRs) model.setImages(images);
            return model;
        } catch (SQLException throwables) {
            Logger.getLogger("ProductMapper").warning(throwables.getMessage());
            return null;
        }

    }
}

