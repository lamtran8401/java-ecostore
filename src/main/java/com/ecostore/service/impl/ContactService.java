package com.ecostore.service.impl;

import com.ecostore.dao.IContactDAO;

import com.ecostore.model.ContactModel;
import com.ecostore.service.IContactService;


import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class ContactService implements IContactService {
    @Inject
    private IContactDAO contactDAO;


    @Override
    public List<ContactModel> findAll() {
        return contactDAO.findAll();
    }

    @Override
    public ContactModel findOneById(long id) {
        return contactDAO.findOneById(id);
    }

    @Override
    public ContactModel insert(ContactModel model) {
        model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Long id = contactDAO.insert(model);
        return contactDAO.findOneById(id);
    }

    @Override
    public ContactModel update(ContactModel model) {
        ContactModel oldContact = contactDAO.findOneById(model.getId());
        model.setCreatedDate(oldContact.getCreatedDate());

        model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if (contactDAO.update(model))
            return contactDAO.findOneById(model.getId());
        return null;
    }

    @Override
    public boolean delete(long[] ids) {
        for (long id : ids) {
            if (!contactDAO.delete(id))
                return false;
        }
        return true;
    }
}
