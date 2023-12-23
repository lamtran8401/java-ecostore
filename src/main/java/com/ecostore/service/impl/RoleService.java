package com.ecostore.service.impl;

import com.ecostore.dao.IRoleDAO;
import com.ecostore.model.RoleModel;
import com.ecostore.service.IRoleService;

import javax.inject.Inject;
import java.util.List;

public class RoleService implements IRoleService {
    @Inject
    private IRoleDAO roleDAO;
    @Override
    public List<RoleModel> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public RoleModel findOneById(long id) {
        return roleDAO.findOneById(id);
    }
}
