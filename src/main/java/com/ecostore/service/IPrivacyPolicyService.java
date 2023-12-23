package com.ecostore.service;

import com.ecostore.model.PrivacyPolicyModel;

import java.util.List;

public interface IPrivacyPolicyService {
    PrivacyPolicyModel findOneByStatus(int status);
    List<PrivacyPolicyModel> findAll();
    PrivacyPolicyModel findOneById(long id);
    PrivacyPolicyModel insert(PrivacyPolicyModel privacyPolicyModel);
    PrivacyPolicyModel update(PrivacyPolicyModel privacyPolicyModel);
    boolean delete(long [] ids);


}
