package com.ecostore.service.impl;

import com.ecostore.dao.IOrderDAO;
import com.ecostore.dao.IOrderDetailDAO;
import com.ecostore.model.OrdersModel;
import com.ecostore.paging.IPageble;
import com.ecostore.service.IOrderDetailService;
import com.ecostore.service.IOrderService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class OrderService implements IOrderService {
    @Inject
    private IOrderDAO orderDAO;

    @Override
    public OrdersModel insert(OrdersModel model) {
        model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Long id = orderDAO.insert(model);
        return orderDAO.findOneById(id);
    }

    @Override
    public OrdersModel update(OrdersModel model) {
        OrdersModel oldOrder = orderDAO.findOneById(model.getId());
        model.setCreatedDate(oldOrder.getCreatedDate());
        model.setCreatedBy(oldOrder.getCreatedBy());
        model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if (orderDAO.update(model)) {
            return orderDAO.findOneById(model.getId());
        }
        return null;
    }

    @Override
    public List<OrdersModel> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public OrdersModel findOneById(long id) {
        return orderDAO.findOneById(id);
    }

    @Override
    public OrdersModel findOneByUserId(long userid) {
        return orderDAO.findOneByUserId(userid);
    }

    @Override
    public int getTotalItems(Long userid) {
        return orderDAO.getTotalItems(userid);
    }

    @Override
    public int getTotalOrders() {
        return orderDAO.getTotalOrders();
    }

    @Override
    public long getRevenue() {
        List<OrdersModel> ordersSuccess = orderDAO.findAllByStatus(3);
        long revenue = 0;
        for (OrdersModel o: ordersSuccess)
            revenue += o.getTotalPrice();
        return revenue;
    }

    @Override
    public List<OrdersModel> findAllByUserId(long userid, IPageble pageble) {
        return orderDAO.findAllByUserId(userid, pageble);
    }


}
