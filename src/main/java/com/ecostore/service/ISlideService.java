package com.ecostore.service;

import com.ecostore.model.SlideModel;

import java.util.List;

public interface ISlideService {
    SlideModel insert(SlideModel model);
    List<SlideModel> findAll();
    List<SlideModel> findAllByStatus();
    SlideModel findOneByStatusAndDisplayorder(int displayorder);
    SlideModel findOneById(long id);
    SlideModel update(SlideModel model);
    boolean delete(long [] ids);
}
