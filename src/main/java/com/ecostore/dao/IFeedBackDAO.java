package com.ecostore.dao;

import com.ecostore.model.FeedBackModel;

import java.util.List;

public interface IFeedBackDAO {
    List<FeedBackModel> findAll();

    List<FeedBackModel> findAllByStatus(int status);
    FeedBackModel findOneById(long id);

    Long insert(FeedBackModel feedBackModel);

    boolean update(FeedBackModel feedBackModel);

    boolean delete(long id);

}
