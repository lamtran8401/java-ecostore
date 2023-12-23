package com.ecostore.service;

import com.ecostore.model.ContactModel;


import java.util.List;

public interface IContactService {

    List<ContactModel> findAll();
    ContactModel findOneById(long id);

    ContactModel insert(ContactModel contactModel);

    ContactModel update (ContactModel contactModel);

    boolean delete(long[] ids);
}