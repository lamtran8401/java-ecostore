package com.ecostore.service.impl;

import com.ecostore.dao.IPrivacyPolicyDAO;
import com.ecostore.model.PrivacyPolicyModel;
import com.ecostore.service.IPrivacyPolicyService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class PrivacyPolicyService implements IPrivacyPolicyService {
    @Inject
    private IPrivacyPolicyDAO privacyPolicyDAO;

    @Override
    public PrivacyPolicyModel findOneByStatus(int status) {
        return privacyPolicyDAO.findOneByStatus(status);
    }

    @Override
    public List<PrivacyPolicyModel> findAll() {
        return privacyPolicyDAO.findAll();
    }

    @Override
    public PrivacyPolicyModel findOneById(long id) {
        return privacyPolicyDAO.findOneById(id);
    }

    @Override
    public PrivacyPolicyModel insert(PrivacyPolicyModel privacyPolicyModel) {
        privacyPolicyModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Long id = privacyPolicyDAO.insert(privacyPolicyModel);
        return privacyPolicyDAO.findOneById(id);
    }

    @Override
    public PrivacyPolicyModel update(PrivacyPolicyModel privacyPolicyModel) {
        PrivacyPolicyModel oldPrivacyPolicyModel = privacyPolicyDAO.findOneById(privacyPolicyModel.getId());
        privacyPolicyModel.setCreatedDate(oldPrivacyPolicyModel.getCreatedDate());
        privacyPolicyModel.setCreatedBy(oldPrivacyPolicyModel.getCreatedBy());
        privacyPolicyModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if (privacyPolicyDAO.update(privacyPolicyModel)) {
            return privacyPolicyDAO.findOneById(privacyPolicyModel.getId());
        }
        return null;
    }

    @Override
    public boolean delete(long[] ids) {
        for (long id : ids) {
            if (!privacyPolicyDAO.delete(id))
                return false;
        }
        return true;
    }
}
