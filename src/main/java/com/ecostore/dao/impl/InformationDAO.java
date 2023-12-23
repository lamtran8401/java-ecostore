package com.ecostore.dao.impl;

import com.ecostore.dao.IInformationDAO;
import com.ecostore.mapper.InformationMapper;
import com.ecostore.model.InformationModel;

import java.util.List;

public class InformationDAO extends AbstractDAO<InformationModel> implements IInformationDAO {
    @Override
    public List<InformationModel> findAll() {
        String sql = "SELECT * FROM information";
        return query(sql, new InformationMapper());
    }

    @Override
    public InformationModel findOneById(long id) {
        String sql = "SELECT * FROM information WHERE id = ?";
        List<InformationModel> informationModels = query(sql, new InformationMapper(), id);
        if (informationModels.size() == 0) return null;
        return informationModels.get(0);
    }

    @Override
    public InformationModel findOneByStatus(int status) {
        String sql = "SELECT * FROM information WHERE status = ?";
        List<InformationModel> informationModels = query(sql, new InformationMapper(), status);
        if (informationModels.size() == 0) return null;
        return informationModels.get(0);
    }

    @Override
    public Long insert(InformationModel model) {
        String sql = "INSERT INTO information (address,phone,email,status,facebooklink,instagramlink,twitterlink,createddate,createdby) VALUES (?,?,?,?,?,?,?,?,?)";
        return insert(sql, model.getAddress(), model.getPhone(), model.getEmail(), model.getStatus(), model.getFacebookLink(), model.getInstagramLink(), model.getTwitterLink(), model.getCreatedDate(), model.getCreatedBy());
    }

    @Override
    public boolean update(InformationModel model) {
        String sql = "UPDATE information SET address = ?, phone = ?, email = ?, status = ?, facebooklink = ?, instagramlink = ?,twitterlink = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
        return update(sql, model.getAddress(), model.getPhone(), model.getEmail(), model.getStatus(), model.getFacebookLink(), model.getInstagramLink(), model.getTwitterLink(), model.getModifiedDate(), model.getModifiedBy(), model.getId());
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM information WHERE id = ?";
        return update(sql, id);
    }
}
