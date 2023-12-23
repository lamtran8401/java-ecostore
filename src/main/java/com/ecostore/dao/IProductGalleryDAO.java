package com.ecostore.dao;


import com.ecostore.model.ProductGalleryModel;

public interface IProductGalleryDAO {

    Long insert(ProductGalleryModel model);

    ProductGalleryModel findOneByProductId(long productId);

    boolean delete(long productId);
}
