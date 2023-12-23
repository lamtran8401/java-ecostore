package com.ecostore.service;

import com.ecostore.model.AboutModel;

import java.util.List;

public interface IAboutService {
    AboutModel findOneById(long id);

    List<AboutModel> findAll();

    AboutModel findOneByStatus(int status);

    AboutModel insert(AboutModel model);

    AboutModel update(AboutModel model);

    boolean delete(long[] ids);
}
