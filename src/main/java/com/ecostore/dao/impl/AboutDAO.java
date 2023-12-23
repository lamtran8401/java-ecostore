package com.ecostore.dao.impl;

import com.ecostore.dao.IAboutDAO;
import com.ecostore.mapper.AboutMapper;
import com.ecostore.model.AboutModel;

import java.util.List;

public class AboutDAO extends AbstractDAO<AboutModel> implements IAboutDAO {
    @Override
    public AboutModel findOneById(long id) {
        String sql = "SELECT * FROM about WHERE id = ?";
        List<AboutModel> abouts = query(sql, new AboutMapper(), id);
        return abouts.isEmpty() ? null : abouts.get(0);
    }

    @Override
    public List<AboutModel> findAll() {
        String sql = "SELECT * FROM about";
        return query(sql, new AboutMapper());
    }

    @Override
    public AboutModel findOneByStatus(int status) {
        String sql = "SELECT * FROM about WHERE status = ?";
        List<AboutModel> list = query(sql, new AboutMapper(), status);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Long insert(AboutModel aboutModel) {
        String sql = "INSERT INTO about (content, status, createddate, createdby) VALUES (?,?,?,?)";
        return insert(sql, aboutModel.getContent(), aboutModel.getStatus(), aboutModel.getCreatedDate(), aboutModel.getCreatedBy());
    }

    @Override
    public boolean update(AboutModel aboutModel) {
        String sql = "UPDATE about SET content = ?, status = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
        return update(sql, aboutModel.getContent(), aboutModel.getStatus(), aboutModel.getModifiedDate(), aboutModel.getModifiedBy(), aboutModel.getId());
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM about WHERE id = ?";
        return update(sql, id);
    }
}
