package com.ecostore.service;

import com.ecostore.model.MenuTypeModel;

import java.util.List;

public interface IMenuTypeService {
    List<MenuTypeModel> findAll();
    MenuTypeModel findOneMenuTypeById(long id);
}
