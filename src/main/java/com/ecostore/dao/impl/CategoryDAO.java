package com.ecostore.dao.impl;

import com.ecostore.dao.ICategoryDAO;
import com.ecostore.mapper.CategoryMapper;
import com.ecostore.model.CategoryModel;
import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {
    @Override
    public List<CategoryModel> findAllByStatus(int status) {
        String sql = "SELECT * FROM category WHERE status = ?";
        return query(sql, new CategoryMapper(), status);
    }

    @Override
    public List<CategoryModel> findAll() {
        String sql = "SELECT * FROM category";
        return query(sql, new CategoryMapper());
    }

    @Override
    public CategoryModel findOneById(long id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        List<CategoryModel> categories = query(sql, new CategoryMapper(), id);
        if (categories.size() == 0) return null;
        return categories.get(0);
    }

    @Override
    public Long insert(CategoryModel categoryModel) {
        String sql = "INSERT INTO category (name,code,status,createddate,createdby) VALUES (?,?,?,?,?)";
        return insert(sql, categoryModel.getName(), categoryModel.getCode(), categoryModel.getStatus(), categoryModel.getCreatedDate(), categoryModel.getCreatedBy());
    }

    @Override
    public boolean update(CategoryModel categoryModel) {
        String sql = "UPDATE category SET name = ?, code = ?, status = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
        return update(sql, categoryModel.getName(), categoryModel.getCode(), categoryModel.getStatus(), categoryModel.getModifiedDate(), categoryModel.getModifiedBy(), categoryModel.getId());
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM category WHERE id = ?";
        return update(sql, id);
    }
}
