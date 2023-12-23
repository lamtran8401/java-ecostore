package com.ecostore.dao.impl;

import com.ecostore.dao.IFeedBackDAO;
import com.ecostore.mapper.FeedBackMapper;
import com.ecostore.model.FeedBackModel;

import java.util.List;

public class FeedBackDAO extends AbstractDAO<FeedBackDAO> implements IFeedBackDAO {
    @Override
    public List<FeedBackModel> findAll() {
        String sql = "SELECT feedback.*, u.fullname  FROM feedback JOIN user u ON feedback.userid = u.id";
        return query(sql, new FeedBackMapper());
    }

    @Override
    public List<FeedBackModel> findAllByStatus(int status) {
        String sql = "SELECT feedback.*, u.fullname, u.avatar FROM feedback JOIN user u ON feedback.userid = u.id WHERE feedback.status = ?";
       return query(sql, new FeedBackMapper(), status);
    }

    @Override
    public FeedBackModel findOneById(long id) {
        String sql = "SELECT * FROM feedback WHERE id=?";
        List<FeedBackModel> feedback = query(sql, new FeedBackMapper(), id);
        if (feedback.size() == 0) return null;
        return feedback.get(0);
    }

    @Override
    public Long insert(FeedBackModel model) {
        String sql = "INSERT INTO feedback(userid, content, status, createddate ,createdby) VALUES (?, ?, ?, ?, ?)";
        return insert(sql, model.getUserId(), model.getContent(), model.getStatus(), model.getCreatedDate(), model.getCreatedBy());
    }

    @Override
    public boolean update(FeedBackModel model) {
        String sql = "UPDATE feedback SET   content = ? , status = ?, modifieddate = ? , modifiedby=?  WHERE id = ?";
        return update(sql, model.getContent(), model.getStatus(), model.getModifiedDate(), model.getModifiedBy(), model.getId());
    }


    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM feedback WHERE id = ?";
        return update(sql, id);
    }
}
