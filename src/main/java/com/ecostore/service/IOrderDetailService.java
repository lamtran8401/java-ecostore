package com.ecostore.service;

import com.ecostore.model.OrderDetailsModel;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailsModel insert(OrderDetailsModel orderDetailsModel);
    List<OrderDetailsModel> findAllByOrderId(long orderId);
    boolean delete(long id);
}
