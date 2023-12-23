package com.ecostore.service;

import com.ecostore.model.InformationModel;

import java.util.List;

public interface IInformationService {
    List<InformationModel> findAll();

    InformationModel findOneById(long id);

    InformationModel findOneByStatus(int status);

    InformationModel insert(InformationModel model);

    InformationModel update(InformationModel model);

    boolean delete(long[] ids);
}
