package com.ecostore.service;

import com.ecostore.model.ProductModel;
import com.ecostore.paging.IPageble;

import java.util.List;

public interface IProductService {
    List<ProductModel> findAllByCategoryCode(String code, IPageble pageble);

    List<ProductModel> findAllSortByCreateddate();

    List<ProductModel> findAllSortByDiscount();

    ProductModel findOneById(long id);

    int getTotalItemsByCategoryCode(String code);

    int getTotalItemsByKeyword(String keyword);

    List<ProductModel> findAllByCategoryId(long categoryId);

    List<ProductModel> findAllByKeyWord(String keyword, IPageble pageble);

    List<ProductModel> findAllByCategoryAndSupplierCode(String category, String supplier, IPageble pageble);

    int getTotalItemsByCategoryAndSupplierCode(String categoryCode, String supplierCode);

    List<ProductModel> findAllByCategoryAndSupplierCodeAndPrice(String categoryCode, String supplierCode, long[] priceFilter, IPageble pageble);

    int getTotalItemsByCategoryAndSupplierCodeAndPrice(String categoryCode, String supplierCode, long[] priceFilter);

    List<ProductModel> findAllByCategoryCodeAndPrice(String code, long[] priceFilter, IPageble pageble);

    int getTotalItemsByCategoryCodeAndPrice(String code, long[] priceFilter);

    int getTotalItemsByStatus(int status);

    List<ProductModel> findAll();

    ProductModel insert(ProductModel productModel);

    ProductModel update(ProductModel productModel);
}
