package com.ecostore.service.impl;

import com.ecostore.dao.IFeedBackDAO;
import com.ecostore.model.FeedBackModel;
import com.ecostore.service.IFeedBackService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;


public class FeedBackService implements IFeedBackService {
    @Inject
    private IFeedBackDAO feedbackDAO;
    @Override
    public List<FeedBackModel> findAll() {
        return feedbackDAO.findAll();
    }


    @Override
    public FeedBackModel findOneById(long id) {
        return feedbackDAO.findOneById(id);
    }

    @Override
    public List<FeedBackModel> findAllByStatus(int status) {
        return feedbackDAO.findAllByStatus(status);
    }

    @Override
    public FeedBackModel insert(FeedBackModel model) {
        model.setCreatedDate( new Timestamp(System.currentTimeMillis()));
        Long id = feedbackDAO.insert(model);
        return feedbackDAO.findOneById(id);
    }

    @Override
    public FeedBackModel update(FeedBackModel model) {
        FeedBackModel oldFeedback = feedbackDAO.findOneById(model.getId()) ;
        model.setCreatedDate(oldFeedback.getCreatedDate());
        model.setCreatedBy(oldFeedback.getCreatedBy());
        model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if (feedbackDAO.update(model)){
            return feedbackDAO.findOneById(model.getId());
        }
        return null;
    }

    @Override
    public boolean delete(long[] ids ) {
        for (long id:ids) {
            if (!feedbackDAO.delete(id))
                return false;
        }
        return true;
    }
}
