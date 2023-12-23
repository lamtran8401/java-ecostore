package com.ecostore.mapper;

import com.ecostore.model.FeedBackModel;
import com.ecostore.model.OrdersModel;
import com.ecostore.model.PaymentModel;
import com.ecostore.model.UserModel;
import com.ecostore.utils.MapperUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersMapper implements IRowMapper<OrdersModel> {
    @Override
    public OrdersModel mapRow(ResultSet resultSet) {
        OrdersModel model = new OrdersModel();
        try {
            model.setId(resultSet.getLong("id"));
            model.setUserId(resultSet.getLong("userid"));
            PaymentModel payment = new PaymentModel();
            model.setPaymentId(resultSet.getLong("paymentid"));
            if(MapperUtil.hasColumn(resultSet,"namepayment"))
                payment.setName(resultSet.getString("namepayment"));
            model.setPayment(payment);
            model.setCustomerName(resultSet.getString("customername"));
            model.setPhone(resultSet.getString("phone"));
            model.setAddress(resultSet.getString("address"));
            model.setMessage(resultSet.getString("message"));
            model.setTotalPrice(resultSet.getLong("totalprice"));
            model.setStatus(resultSet.getInt("status"));
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
