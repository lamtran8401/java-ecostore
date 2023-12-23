package com.ecostore.mapper;

import com.ecostore.model.OrderDetailsModel;
import com.ecostore.model.PaymentModel;
import com.ecostore.model.ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailMapper implements IRowMapper<OrderDetailsModel> {
    @Override
    public OrderDetailsModel mapRow(ResultSet resultSet) {
        OrderDetailsModel model = new OrderDetailsModel();
        try {
            model.setId(resultSet.getLong("id"));
            model.setOrderId(resultSet.getLong("orderid"));
            ProductModel productModel = new ProductModel();
            model.setProductId(resultSet.getLong("productid"));
            productModel.setName(resultSet.getString("nameproduct"));
            model.setProduct(productModel);
            model.setTotalPrice(resultSet.getLong("totalprice"));
            model.setUnitPrice(resultSet.getLong("unitprice"));
            model.setQuantity(resultSet.getInt("quantity"));
            return model;
        } catch (SQLException throwables) {
            return null;
        }
    }
}
