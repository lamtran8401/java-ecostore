package com.ecostore.service;

import com.ecostore.model.CommentModel;

import java.util.List;

public interface ICommentService {

    List<CommentModel> findAll();

    CommentModel findOneById(long id);

    CommentModel insert(CommentModel commentModel);

    CommentModel update(CommentModel commentModel);

    boolean delete(long [] ids);

    List<CommentModel> findAllByProductId(long productId);
}
