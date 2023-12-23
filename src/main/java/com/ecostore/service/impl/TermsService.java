package com.ecostore.service.impl;

import com.ecostore.dao.ITermsDAO;
import com.ecostore.model.TermsModel;
import com.ecostore.service.ITermsService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class TermsService implements ITermsService {
    @Inject
    private ITermsDAO termsDAO;
    @Override
    public TermsModel findOneByStatus(int status) {
        return termsDAO.findOneByStatus(status);
    }

    @Override
    public List<TermsModel> findAll() {
        return termsDAO.findAll();

    }
    @Override
    public TermsModel findOneById(long id) {
        return termsDAO.findOneById(id);
    }

    @Override
    public TermsModel insert(TermsModel termsModel) {
        termsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Long id = termsDAO.insert(termsModel);
        return termsDAO.findOneById(id);
    }

    @Override
    public TermsModel update(TermsModel termsModel) {
        TermsModel oldTermsModel = termsDAO.findOneById(termsModel.getId());
        termsModel.setCreatedDate(oldTermsModel.getCreatedDate());
        termsModel.setCreatedBy(oldTermsModel.getCreatedBy());
        termsModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if (termsDAO.update(termsModel)){
            return termsDAO.findOneById(termsModel.getId());
        }
        return null;
    }

    @Override
    public boolean delete(long[] ids) {
        for (long id : ids) {
            if (!termsDAO.delete(id))
            return false;
        }
        return true;
    }
}
