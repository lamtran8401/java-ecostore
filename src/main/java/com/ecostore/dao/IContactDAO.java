package com.ecostore.dao;

import com.ecostore.model.ContactModel;

import java.util.List;

public interface IContactDAO {

    List<ContactModel> findAll();

    ContactModel findOneById(long id);

    Long insert(ContactModel contactModel);

    boolean update(ContactModel contactModel);

    boolean delete(long id);
}
