package com.ecostore.service;

import com.ecostore.model.TermsModel;

import java.util.List;


public interface ITermsService {
    TermsModel findOneByStatus(int status);
    List<TermsModel> findAll();
    TermsModel findOneById(long id);
    TermsModel insert(TermsModel termsModel);
    TermsModel update(TermsModel termsModel);
    boolean delete(long [] ids);

}
