package com.ecostore.service.impl;

import com.ecostore.dao.ISupplierDAO;
import com.ecostore.model.SupplierModel;
import com.ecostore.service.ISupplierService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;


public class SupplierService implements ISupplierService {
    @Inject
    ISupplierDAO supplierDAO;

    @Override
    public List<SupplierModel> findAllByStatus(int status) {
        return supplierDAO.findAllByStatus(status);
    }

    @Override
    public List<SupplierModel> findAll() {
        return supplierDAO.findAll();
    }

    @Override
    public SupplierModel findOneById(long id) {
        return supplierDAO.findOneById(id);
    }

    @Override
    public SupplierModel insert(SupplierModel model) {
        model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Long id = supplierDAO.insert(model);
        return supplierDAO.findOneById(id);
    }

    @Override
    public SupplierModel update(SupplierModel model) {
        SupplierModel oldSupplier = supplierDAO.findOneById(model.getId());
        model.setCreatedDate(oldSupplier.getCreatedDate());
        model.setCreatedBy(oldSupplier.getCreatedBy());
        model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if (supplierDAO.update(model))
            return supplierDAO.findOneById(model.getId());
        return null;
    }

    @Override
    public boolean delete(long[] ids) {
        for (long id : ids) {
            if (!supplierDAO.delete(id))
                return false;
        }
        return true;
    }

    @Override
    public List<SupplierModel> findAllByCategoryCode(String code) {
        return supplierDAO.findAllByCategoryCode(code);
    }
}
