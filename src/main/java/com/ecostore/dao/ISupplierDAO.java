package com.ecostore.dao;

import com.ecostore.model.SupplierModel;


import java.util.List;

public interface ISupplierDAO {
    List<SupplierModel> findAllByStatus(int status);
    List<SupplierModel> findAll();

    SupplierModel findOneById(long id);

    Long insert(SupplierModel supplierModel);

    boolean update(SupplierModel supplierModel);

    boolean delete(long id);

    List<SupplierModel> findAllByCategoryCode(String code);
}
