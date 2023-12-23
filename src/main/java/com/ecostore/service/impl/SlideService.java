package com.ecostore.service.impl;

import com.ecostore.dao.ISlideDAO;
import com.ecostore.model.SlideModel;
import com.ecostore.service.ISlideService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class SlideService implements ISlideService {
    @Inject
    private ISlideDAO slideDAO;

    @Override
    public SlideModel insert(SlideModel model) {
        model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Long id = slideDAO.insert(model);
        return slideDAO.findOneById(id);
    }

    @Override
    public List<SlideModel> findAll() {
        return slideDAO.findAll();
    }

    @Override
    public List<SlideModel> findAllByStatus() {
        return slideDAO.findAllByStatus();
    }

    @Override
    public SlideModel findOneByStatusAndDisplayorder(int displayorder) {
        return slideDAO.findOneByStatusAndDisplayorder(displayorder);
    }

    @Override
    public SlideModel findOneById(long id) {
        return slideDAO.findOneById(id);
    }

    @Override
    public SlideModel update(SlideModel model) {
        SlideModel slideOld = slideDAO.findOneById(model.getId());
        model.setCreatedDate(slideOld.getCreatedDate());
        model.setCreatedBy(slideOld.getCreatedBy());
        model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if (slideDAO.update(model)){
            return slideDAO.findOneById(model.getId());
        } else {
            return null;
        }

    }

    @Override
    public boolean delete(long[] ids) {
        for (long id: ids) {
            if (!slideDAO.delete(id))
            return false;
        }
        return true;
    }
}
