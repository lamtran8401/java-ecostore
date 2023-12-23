package com.ecostore.service;

import com.ecostore.model.RoleModel;

import java.util.List;

public interface IRoleService {
    List<RoleModel> findAll();
    RoleModel findOneById(long id);
}
