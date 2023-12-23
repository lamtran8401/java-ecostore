package com.ecostore.dao.impl;

import com.ecostore.dao.ISupplierDAO;
import com.ecostore.mapper.SupplierMapper;
import com.ecostore.model.SupplierModel;

import java.util.List;

public class SupplierDAO extends AbstractDAO<SupplierModel> implements ISupplierDAO {
    @Override
    public List<SupplierModel> findAllByStatus(int status) {
        String sql = "SELECT * FROM supplier WHERE status = ?";
        return query(sql, new SupplierMapper(), status);
    }

    @Override
    public List<SupplierModel> findAll() {
        String sql = "SELECT * FROM supplier";
        return query(sql, new SupplierMapper());
    }

    @Override
    public SupplierModel findOneById(long id) {
        String sql = "SELECT * FROM supplier WHERE id = ?";
        List<SupplierModel> supplier = query(sql, new SupplierMapper(), id);
        if (supplier.size() == 0) return null;
        return supplier.get(0);
    }

    @Override
    public Long insert(SupplierModel supplierModel) {
        String sql = "INSERT INTO supplier(name, code, status, createddate ,createdby ) VALUES (?, ?, ?, ?, ?)";
        return insert(sql, supplierModel.getName(), supplierModel.getCode(), supplierModel.getStatus(), supplierModel.getCreatedDate(), supplierModel.getCreatedBy());
    }

    @Override
    public boolean update(SupplierModel supplierModel) {
        String sql = "UPDATE supplier SET name = ?, code = ?, status = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
        return update(sql, supplierModel.getName(), supplierModel.getCode(), supplierModel.getStatus(), supplierModel.getModifiedDate(), supplierModel.getModifiedBy(), supplierModel.getId());
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM supplier WHERE id = ?";
        return update(sql, id);
    }

    @Override
    public List<SupplierModel> findAllByCategoryCode(String code) {
        String sql = "SELECT DISTINCT supplier.* FROM product JOIN category on product.categoryid = category.id JOIN supplier on product.supplierid = supplier.id WHERE category.code = ? AND supplier.status = 1";
        return query(sql, new SupplierMapper(), code);
    }
}
