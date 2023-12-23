package com.ecostore.service.impl;

import com.ecostore.dao.IAboutDAO;
import com.ecostore.model.AboutModel;
import com.ecostore.service.IAboutService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class AboutService implements IAboutService {
    @Inject
    private IAboutDAO aboutDAO;

    @Override
    public AboutModel findOneById(long id) {
        return aboutDAO.findOneById(id);
    }

    @Override
    public List<AboutModel> findAll() {
        return aboutDAO.findAll();
    }

    @Override
    public AboutModel findOneByStatus(int status) {
        return aboutDAO.findOneByStatus(status);
    }

    @Override
    public AboutModel insert(AboutModel model) {
        model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Long id = aboutDAO.insert(model);
        return aboutDAO.findOneById(id);
    }

    @Override
    public AboutModel update(AboutModel model) {
        AboutModel oldAbout = aboutDAO.findOneById(model.getId());
        model.setCreatedDate(oldAbout.getCreatedDate());
        model.setCreatedBy(oldAbout.getCreatedBy());
        model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if(aboutDAO.update(model))
            return aboutDAO.findOneById(model.getId());
        return null;
    }

    @Override
    public boolean delete(long[] ids) {
        for (long id: ids) {
            if(!aboutDAO.delete(id))
                return false;
        }
        return true;
    }
}
