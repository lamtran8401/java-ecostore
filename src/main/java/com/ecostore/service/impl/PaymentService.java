package com.ecostore.service.impl;

import com.ecostore.dao.IPaymentDAO;
import com.ecostore.model.PaymentModel;
import com.ecostore.service.IPaymentService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class PaymentService implements IPaymentService {
    @Inject
    IPaymentDAO paymentDAO;
    @Override
    public List<PaymentModel> findAllByStatus(int status) {
        return paymentDAO.findAllByStatus(status);
    }

    @Override
    public List<PaymentModel> findAll() {
        return paymentDAO.findAll();
    }

    @Override
    public PaymentModel findOneById(long id) {
        return paymentDAO.findOneById(id);
    }

    @Override
    public PaymentModel insert(PaymentModel model) {
        model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Long id = paymentDAO.insert(model);
        return paymentDAO.findOneById(id);
    }

    @Override
    public PaymentModel update(PaymentModel model) {
        PaymentModel oldPayment = paymentDAO.findOneById(model.getId());
        model.setCreatedDate(oldPayment.getCreatedDate());
        model.setCreatedBy(oldPayment.getCreatedBy());
        model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if (paymentDAO.update(model)){
            return paymentDAO.findOneById(model.getId());
        }

        return null;
    }

    @Override
    public boolean delete(long[] ids) {
        for (long id : ids) {
            if (!paymentDAO.delete(id))
                return false;
        }
        return true;
    }
}
