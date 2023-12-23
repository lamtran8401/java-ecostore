package com.ecostore.dao;

import com.ecostore.model.PrivacyPolicyModel;

import java.util.List;

public interface IPrivacyPolicyDAO {
    PrivacyPolicyModel findOneByStatus(int status);
    List<PrivacyPolicyModel> findAll();
    PrivacyPolicyModel findOneById(long id);
    Long insert(PrivacyPolicyModel privacyPolicyModel);
    boolean update(PrivacyPolicyModel privacyPolicyModel);
    boolean delete(long id);




}
