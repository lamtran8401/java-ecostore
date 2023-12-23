package com.ecostore.service.impl;

import com.ecostore.dao.ICommentDAO;
import com.ecostore.model.CommentModel;
import com.ecostore.service.ICommentService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class CommentService implements ICommentService {
    @Inject
    private ICommentDAO commentDAO;

    @Override
    public List<CommentModel> findAll() {
        return commentDAO.findAll();
    }

    @Override
    public CommentModel findOneById(long id) {
        return commentDAO.findOneById(id);
    }

    @Override
    public CommentModel insert(CommentModel model) {
        model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Long id = commentDAO.insert(model);
        return commentDAO.findOneById(id);
    }

    @Override
    public CommentModel update(CommentModel model) {
        CommentModel oldComment = commentDAO.findOneById(model.getId());
        model.setCreatedDate(oldComment.getCreatedDate());
        model.setCreatedBy(oldComment.getCreatedBy());
        model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        if (commentDAO.update(model))
            return commentDAO.findOneById(model.getId());
        return null;
    }

    @Override
    public boolean delete(long[] ids) {
        for (long id : ids) {
            if (!commentDAO.delete(id))
                return false;
        }
        return true;
    }

    @Override
    public List<CommentModel> findAllByProductId(long productId) {
        return commentDAO.findAllByProductId(productId);
    }
}
