package com.ecostore.dao;

import com.ecostore.model.TermsModel;

import java.util.List;

public interface ITermsDAO {
    TermsModel findOneByStatus(int status);
    List<TermsModel> findAll();
    TermsModel findOneById(long id);
    Long insert(TermsModel termsModel);
    boolean update(TermsModel termsModel);
    boolean delete(long id);
}
