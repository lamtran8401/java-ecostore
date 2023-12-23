package com.ecostore.dao;

import com.ecostore.model.OrdersModel;
import com.ecostore.paging.IPageble;

import java.util.List;

public interface IOrderDAO {
    long insert(OrdersModel model);

    boolean update(OrdersModel model);

    OrdersModel findOneById(long id);

    List<OrdersModel> findAll();

    OrdersModel findOneByUserId(Long userid);

    List<OrdersModel> findAllByUserId(long userid, IPageble pageble);

    int getTotalItems(Long userid);

    int getTotalOrders();

    List<OrdersModel> findAllByStatus(int status);
}
