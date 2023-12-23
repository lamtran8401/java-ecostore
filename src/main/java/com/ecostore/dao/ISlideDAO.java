package com.ecostore.dao;

import com.ecostore.model.SlideModel;

import java.util.List;

public interface ISlideDAO {
    Long insert(SlideModel model);
    List<SlideModel> findAll();
    List<SlideModel> findAllByStatus();
    SlideModel findOneByStatusAndDisplayorder(int displayorder);
    SlideModel findOneById(long id);
    boolean update(SlideModel model);
    boolean delete(long id);
}
