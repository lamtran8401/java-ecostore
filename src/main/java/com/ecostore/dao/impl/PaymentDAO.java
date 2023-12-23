package com.ecostore.dao.impl;

import com.ecostore.dao.IPaymentDAO;
import com.ecostore.mapper.PaymentMapper;
import com.ecostore.model.PaymentModel;
import com.ecostore.model.SupplierModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAO extends AbstractDAO<PaymentModel> implements IPaymentDAO {
    @Override
    public List<PaymentModel> findAllByStatus(int status) {
        String sql = "SELECT * FROM payment WHERE status = ?";
        return query(sql, new PaymentMapper(), status);
    }

    @Override
    public List<PaymentModel> findAll() {
        String sql = "SELECT * FROM payment";
        return query(sql, new PaymentMapper());
    }

    @Override
    public PaymentModel findOneById(long id) {
        String sql = "SELECT * FROM payment WHERE id = ?";
        List<PaymentModel> payment = query(sql, new PaymentMapper(), id);
        if (payment.size() == 0) return null;
        return payment.get(0);
    }

    @Override
    public Long insert(PaymentModel paymentModel) {
        String sql = "INSERT INTO payment(name, status, createddate ,createdby ) VALUES (?, ?, ?, ?)";
        return insert(sql, paymentModel.getName(), paymentModel.getStatus(), paymentModel.getCreatedDate(), paymentModel.getCreatedBy());
    }

    @Override
    public boolean update(PaymentModel paymentModel) {
        String sql = "UPDATE payment SET name = ?, status = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
        return update(sql, paymentModel.getName(), paymentModel.getStatus(), paymentModel.getModifiedDate(), paymentModel.getModifiedBy(), paymentModel.getId());
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM payment WHERE id = ?";
        return update(sql, id);
    }
}
