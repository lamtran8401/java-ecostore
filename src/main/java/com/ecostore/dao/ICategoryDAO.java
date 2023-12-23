package com.ecostore.dao;

import com.ecostore.model.CategoryModel;

import java.util.List;

public interface ICategoryDAO {
    List<CategoryModel> findAllByStatus(int status);

    List<CategoryModel> findAll();

    CategoryModel findOneById(long id);

    Long insert(CategoryModel categoryModel);

    boolean update(CategoryModel categoryModel);

    boolean delete(long id);
}
