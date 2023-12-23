package com.ecostore.service;

import com.ecostore.model.SupplierModel;

import java.util.List;

public interface ISupplierService {
    List<SupplierModel> findAllByStatus(int status);
    List<SupplierModel> findAll();
    SupplierModel findOneById(long id);

    SupplierModel insert(SupplierModel supplierModel);

    SupplierModel update (SupplierModel supplierModel);

    boolean delete(long[] ids);

    List<SupplierModel> findAllByCategoryCode(String code);
}
