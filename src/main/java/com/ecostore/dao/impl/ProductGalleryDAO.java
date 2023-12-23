package com.ecostore.dao.impl;

import com.ecostore.dao.IProductGalleryDAO;
import com.ecostore.mapper.ProductGalleryMapper;
import com.ecostore.model.ProductGalleryModel;

import java.util.List;


public class ProductGalleryDAO extends AbstractDAO<ProductGalleryModel> implements IProductGalleryDAO {

    @Override
    public Long insert(ProductGalleryModel model) {
        String sql = "INSERT INTO productgallery (imagelink, productid) VALUES (?,?)";
        return insert(sql, model.getImageLink(), model.getProductId());
    }

    @Override
    public ProductGalleryModel findOneByProductId(long productId) {
       String sql = "SELECT * FROM productgallery WHERE productid = ? LIMIT 1";
       List<ProductGalleryModel> galleries = query(sql, new ProductGalleryMapper(), productId);
       return galleries.isEmpty() ? null : galleries.get(0);
    }

    @Override
    public boolean delete(long productId) {
        String sql = "DELETE FROM productgallery WHERE productid = ?";
        return update(sql, productId);
    }
}
