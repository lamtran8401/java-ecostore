package com.ecostore.service;

import com.ecostore.model.PaymentModel;

import java.util.List;

public interface IPaymentService {
    List<PaymentModel> findAllByStatus(int status);
    List<PaymentModel> findAll();
    PaymentModel findOneById(long id);

    PaymentModel insert(PaymentModel paymentModel);

    PaymentModel update (PaymentModel paymentModel);

    boolean delete(long[] ids);
}
