package com.ecostore.dao;
import com.ecostore.model.PaymentModel;
import java.util.List;

public interface IPaymentDAO {
    List<PaymentModel> findAllByStatus(int status);
    List<PaymentModel> findAll();

    PaymentModel findOneById(long id);

    Long insert(PaymentModel paymentModel);

    boolean update(PaymentModel paymentModel);

    boolean delete(long id);
}
