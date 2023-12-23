package com.ecostore.dao;

import com.ecostore.model.MenuTypeModel;

import java.util.List;

public interface IMenuTypeDAO {
    List<MenuTypeModel> findAll();
    MenuTypeModel findOneMenuTypeById(long id);
}
