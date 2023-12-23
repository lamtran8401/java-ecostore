package com.ecostore.dao.impl;

import com.ecostore.dao.ITermsDAO;
import com.ecostore.mapper.TermsMapper;
import com.ecostore.model.TermsModel;

import java.util.List;

public class TermsDAO extends AbstractDAO<TermsModel> implements ITermsDAO {
    @Override
    public TermsModel findOneByStatus(int status) {
        String sql = "SELECT * FROM terms WHERE status = ?";
        List<TermsModel> list = query(sql , new TermsMapper() , status);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public List<TermsModel> findAll() {
        String sql = "SELECT * FROM terms";
        return query(sql, new TermsMapper());
    }

    @Override
    public TermsModel findOneById(long id) {
        String sql = "SELECT * FROM terms WHERE id = ?";
        List<TermsModel> termsModels = query(sql, new TermsMapper(), id);
        return termsModels.isEmpty() ? null : termsModels.get(0);
    }

    @Override
    public Long insert(TermsModel termsModel) {
        String sql = "INSERT INTO terms (content,status,createddate,createdby) VALUES (?,?,?,?)";
        return insert(sql, termsModel.getContent(), termsModel.getStatus(), termsModel.getCreatedDate(), termsModel.getCreatedBy());
    }

    @Override
    public boolean update(TermsModel termsModel) {
        String sql = "UPDATE terms SET content = ?, status = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
        return update(sql, termsModel.getContent(), termsModel.getStatus(), termsModel.getModifiedDate(), termsModel.getModifiedBy(), termsModel.getId());
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM terms WHERE id = ?";
        return update(sql,id);
    }
}
