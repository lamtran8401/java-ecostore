package com.ecostore.dao.impl;

import com.ecostore.dao.ICommentDAO;
import com.ecostore.mapper.CommentMapper;

import com.ecostore.model.CommentModel;


import java.util.List;

public class CommentDAO extends AbstractDAO<CommentDAO> implements ICommentDAO {

    @Override
    public List<CommentModel> findAll() {
        String sql = "SELECT c.* , u.fullname, u.avatar, p.name FROM comment c , user u, product p WHERE c.userid = u.id AND c.productid = p.id ";
        return query(sql, new CommentMapper());
    }

    @Override
    public CommentModel findOneById(long id) {
        String sql = "SELECT c.* , u.fullname, u.avatar, p.name FROM comment c , user u, product p WHERE c.userid = u.id AND c.productid = p.id AND c.id = ?";
        List<CommentModel> comment = query(sql, new CommentMapper(), id);
        if (comment.size() == 0) return null;
        return comment.get(0);
    }

    @Override
    public Long insert(CommentModel model) {
        String sql = "INSERT INTO comment (userid, productid, content, status, createddate, createdby ) VALUES (?, ?, ?, ?, ?, ?)";
        return insert(sql, model.getUserId(), model.getProductId(), model.getContent(), model.getStatus(), model.getCreatedDate(), model.getCreatedBy());
    }

    @Override
    public boolean update(CommentModel model) {
        String sql = "UPDATE comment SET content = ? , status = ? , modifieddate=? , modifiedby =?  WHERE id=?";
        return update(sql, model.getContent(), model.getStatus(), model.getModifiedDate(), model.getModifiedBy(), model.getId());
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM comment WHERE id = ?";
        return update(sql, id);
    }

    @Override
    public List<CommentModel> findAllByProductId(long productId) {
        String sql = "SELECT c.* , u.fullname, u.avatar, p.name FROM comment c , user u, product p WHERE c.userid = u.id AND c.productid = p.id AND c.productid = ? AND c.status = 1 ORDER BY c.createddate DESC";
        return query(sql, new CommentMapper(), productId);
    }
}
