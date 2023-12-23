package com.ecostore.service;

import com.ecostore.model.FeedBackModel;

import java.util.List;

public interface IFeedBackService {
    List<FeedBackModel> findAll();

    FeedBackModel findOneById(long id);
    List<FeedBackModel> findAllByStatus(int status);

    FeedBackModel insert(FeedBackModel feedBackModel);

    FeedBackModel update(FeedBackModel feedBackModel);

    boolean delete(long[] ids);
}
