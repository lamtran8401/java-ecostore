package com.ecostore.dao;

import com.ecostore.model.InformationModel;

import java.util.List;

public interface IInformationDAO {
    List<InformationModel> findAll();

    InformationModel findOneById(long id);

    InformationModel findOneByStatus(int status);

    Long insert(InformationModel model);

    boolean update(InformationModel model);

    boolean delete(long id);
}
