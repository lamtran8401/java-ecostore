package com.ecostore.service.impl;

import com.ecostore.dao.IOrderDetailDAO;
import com.ecostore.model.OrderDetailsModel;
import com.ecostore.service.IOrderDetailService;

import javax.inject.Inject;
import java.util.List;

public class OrderDetailService implements IOrderDetailService {
    @Inject
    private IOrderDetailDAO orderDetailDAO;

    @Override
    public OrderDetailsModel insert(OrderDetailsModel model) {
        Long id = orderDetailDAO.insert(model);
        return orderDetailDAO.findOneById(id);
    }

    @Override
    public List<OrderDetailsModel> findAllByOrderId(long orderId) { 
        return orderDetailDAO.findAllByOrderId(orderId);
    }

    @Override
    public boolean delete(long id) {
        return orderDetailDAO.delete(id);
    }


}
