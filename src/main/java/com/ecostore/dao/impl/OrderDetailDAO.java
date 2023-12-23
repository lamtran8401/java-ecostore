package com.ecostore.dao.impl;

import com.ecostore.dao.IOrderDetailDAO;
import com.ecostore.mapper.AboutMapper;
import com.ecostore.mapper.OrderDetailMapper;
import com.ecostore.model.AboutModel;
import com.ecostore.model.OrderDetailsModel;

import java.util.List;

public class OrderDetailDAO extends AbstractDAO<OrderDetailsModel> implements IOrderDetailDAO {

    @Override
    public long insert(OrderDetailsModel model) {
        String sql = "INSERT INTO orderdetails (orderid, productid, unitprice, quantity, totalprice) VALUES (?,?,?,?,?)";
        return insert(sql, model.getOrderId(), model.getProductId(), model.getUnitPrice(), model.getQuantity(), model.getTotalPrice());
    }

    @Override
    public OrderDetailsModel findOneById(long id) {
        String sql = "SELECT * FROM orderdetails WHERE id = ?";
        List<OrderDetailsModel> orderDetailsModels = query(sql, new OrderDetailMapper(), id);
        return orderDetailsModels.isEmpty() ? null : orderDetailsModels.get(0);
    }

    @Override
    public List<OrderDetailsModel> findAllByOrderId(long orderId) {
        String sql = "SELECT orderdetails.*, product.name AS nameproduct FROM orderdetails JOIN product ON orderdetails.productid = product.id WHERE orderid  = ?";
        return query(sql, new OrderDetailMapper(), orderId);
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM orderdetails WHERE id = ?";
        return update(sql,id);
    }
}
