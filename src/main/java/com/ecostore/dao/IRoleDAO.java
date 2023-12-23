package com.ecostore.dao;


import com.ecostore.model.RoleModel;

import java.util.List;

public interface IRoleDAO {
    List<RoleModel> findAll();
    RoleModel findOneById(long id);
}
