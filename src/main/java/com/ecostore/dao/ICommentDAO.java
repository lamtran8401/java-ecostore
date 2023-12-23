package com.ecostore.dao;

import com.ecostore.model.CommentModel;

import java.util.List;

public interface ICommentDAO {

    List<CommentModel> findAll();

    CommentModel findOneById(long id);

    Long insert(CommentModel commentModel);

    boolean update(CommentModel commentModel);

    boolean delete(long id);

    List<CommentModel> findAllByProductId(long productId);
}
