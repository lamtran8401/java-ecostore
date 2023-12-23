package com.ecostore.service;

import com.ecostore.model.OrdersModel;
import com.ecostore.paging.IPageble;

import java.util.List;

public interface IOrderService {
    OrdersModel insert(OrdersModel model);
    OrdersModel update(OrdersModel model);
    List<OrdersModel> findAll();
    OrdersModel findOneById(long id);
    List<OrdersModel> findAllByUserId(long userid, IPageble pageble);
    OrdersModel findOneByUserId(long userid);
    int getTotalItems(Long userid);

    int getTotalOrders();

    long getRevenue();
}
