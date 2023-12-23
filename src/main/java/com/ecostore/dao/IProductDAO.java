package com.ecostore.dao;

import com.ecostore.model.ProductModel;
import com.ecostore.paging.IPageble;

import java.util.List;

public interface IProductDAO {

    List<ProductModel> findAllByCategoryCode(String code, IPageble pageble);

    List<ProductModel> findAllSortByCreateddate();

    List<ProductModel> findAllSortByDiscount();

    ProductModel findOneById(long id);

    int getTotalItemsByCategoryCode(String code);

    int getTotalItemsByKeyword(String keyword);

    int getTotalItemsByCategoryAndSupplierCode(String categoryCode, String supplierCode);

    List<ProductModel> findAllByCategoryId(long categoryId);

    List<ProductModel> findAllByKeyWord(String keyword, IPageble pageble);

    List<ProductModel> findAllByCategoryAndSupplierCode(String category, String supplier, IPageble pageble);

    List<ProductModel> findAllByCategoryAndSupplierCodeAndPrice(String categoryCode, String supplierCode, long[] priceFilter, IPageble pageble);

    int getTotalItemsByCategoryAndSupplierCodeAndPrice(String categoryCode, String supplierCode, long[] priceFilter);

    List<ProductModel> findAllByCategoryCodeAndPrice(String categoryCode, long[] priceFilter, IPageble pageble);

    int getTotalItemsByCategoryCodeAndPrice(String categoryCode, long[] priceFilter);

    int getTotalItemsByStatus(int status);

    List<ProductModel> findAll();

    Long insert(ProductModel productModel);

    boolean update(ProductModel productModel);
}
