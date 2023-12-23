package com.ecostore.dao;

import com.ecostore.model.CategoryModel;
import com.ecostore.model.MenuModel;

import java.awt.*;
import java.util.List;

public interface IMenuDAO {
    List<MenuModel> findAllByMenuTypeId(long menuTypeId, int status);

    List<MenuModel> findAll();

    MenuModel findOneById(long id);

    Long insert(MenuModel menuModel);

    boolean update(MenuModel menuModel);

    boolean delete(long id);

}
