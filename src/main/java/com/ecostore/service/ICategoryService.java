package com.ecostore.service;

import com.ecostore.model.CategoryModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryModel> findAllByStatus(int status);
    List<CategoryModel> findAll();

    CategoryModel findOneById(long id);

    CategoryModel insert(CategoryModel model);

    CategoryModel update(CategoryModel model);

    boolean delete(long[] ids);

}
