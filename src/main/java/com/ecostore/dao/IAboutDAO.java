package com.ecostore.dao;

import com.ecostore.model.AboutModel;

import java.util.List;

public interface IAboutDAO {

    AboutModel findOneById(long id);

    List<AboutModel> findAll();

    AboutModel findOneByStatus(int status);

    Long insert(AboutModel aboutModel);

    boolean update(AboutModel aboutModel);

    boolean delete(long id);
}
