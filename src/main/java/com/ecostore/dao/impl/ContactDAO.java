package com.ecostore.dao.impl;


import com.ecostore.dao.IContactDAO;


import com.ecostore.mapper.ContactMapper;
import com.ecostore.model.ContactModel;


import java.util.List;


public class ContactDAO extends AbstractDAO<ContactDAO> implements IContactDAO {


    @Override
    public List<ContactModel> findAll() {
        String sql = "SELECT * FROM contact";
        return query(sql, new ContactMapper());
    }

    @Override
    public ContactModel findOneById(long id) {
        String sql = "SELECT * FROM contact WHERE id = ?";
        List<ContactModel> contact = query(sql, new ContactMapper(), id);
        if (contact.size() == 0) return null;
        return contact.get(0);
    }

    @Override
    public Long insert(ContactModel contactModel) {
        String sql = "INSERT INTO contact (fullname,email,content,status,createddate) VALUES (?,?,?,?,?)";
        return insert(sql, contactModel.getFullname(), contactModel.getEmail(), contactModel.getContent(),contactModel.getStatus(), contactModel.getCreatedDate());
    }

    @Override
    public boolean update(ContactModel contactModel) {
        String sql = "UPDATE contact SET fullname = ?, email = ?, content = ?, status = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
        return update(sql, contactModel.getFullname(), contactModel.getEmail(), contactModel.getContent(),contactModel.getStatus(), contactModel.getModifiedDate(), contactModel.getModifiedBy(), contactModel.getId());

    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM contact WHERE id = ?";
        return update(sql, id);
    }
}
