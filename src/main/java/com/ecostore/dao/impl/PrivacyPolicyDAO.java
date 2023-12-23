package com.ecostore.dao.impl;

import com.ecostore.dao.IPrivacyPolicyDAO;
import com.ecostore.mapper.PrivacyPolicyMapper;
import com.ecostore.mapper.TermsMapper;
import com.ecostore.model.PrivacyPolicyModel;

import java.util.List;

public class PrivacyPolicyDAO extends AbstractDAO<PrivacyPolicyDAO> implements IPrivacyPolicyDAO {
    @Override
    public PrivacyPolicyModel findOneByStatus(int status) {
        String sql = "SELECT * FROM privacypolicy WHERE status = ?";
        List<PrivacyPolicyModel> list = query(sql, new PrivacyPolicyMapper(), status);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public List<PrivacyPolicyModel> findAll() {
        String sql = "SELECT * FROM privacypolicy";
        return query(sql, new PrivacyPolicyMapper());
    }

    @Override
    public PrivacyPolicyModel findOneById(long id) {
        String sql = "SELECT * FROM privacypolicy WHERE id = ?";
        List<PrivacyPolicyModel> privacyPolicyModels = query(sql, new PrivacyPolicyMapper(), id);
        return privacyPolicyModels.isEmpty() ? null : privacyPolicyModels.get(0);
    }

    @Override
    public Long insert(PrivacyPolicyModel privacyPolicyModel) {
        String sql = "INSERT INTO privacypolicy (content,status,createddate,createdby) VALUES (?,?,?,?)";
        return insert(sql, privacyPolicyModel.getContent(), privacyPolicyModel.getStatus(), privacyPolicyModel.getCreatedDate(), privacyPolicyModel.getCreatedBy());
    }

    @Override
    public boolean update(PrivacyPolicyModel privacyPolicyModel) {
        String sql = "UPDATE privacypolicy SET content = ?, status = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
        return update(sql, privacyPolicyModel.getContent(), privacyPolicyModel.getStatus(),
                privacyPolicyModel.getModifiedDate(), privacyPolicyModel.getModifiedBy(), privacyPolicyModel.getId());
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM privacypolicy WHERE id = ?";
        return update(sql, id);
    }

}
