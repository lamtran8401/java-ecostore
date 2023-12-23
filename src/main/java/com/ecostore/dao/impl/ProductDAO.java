package com.ecostore.dao.impl;

import com.ecostore.dao.IProductDAO;

import com.ecostore.mapper.ProductMapper;

import com.ecostore.model.ProductModel;
import com.ecostore.paging.IPageble;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO {

    @Override
    public List<ProductModel> findAllSortByCreateddate() {
        String sql = "SELECT P.*, I.imagelink FROM product P join productgallery I on P.id = I.productid  WHERE status = 1 ORDER BY createddate DESC";
        return query(sql, new ProductMapper());
    }

    @Override
    public List<ProductModel> findAllSortByDiscount() {
        String sql = "SELECT P.*, I.imagelink FROM product P join productgallery I on P.id = I.productid  WHERE status = 1 ORDER BY discount DESC ";
        return query(sql, new ProductMapper());
    }

    @Override
    public ProductModel findOneById(long id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT P.*, I.imagelink, C.name AS cname, S.name as sname");
        sql.append(" FROM product P join productgallery I on P.id = I.productid join category C on P.categoryid = C.id join supplier S on P.supplierid = S.id");
        sql.append(" WHERE P.id = ?");
        List<ProductModel> products = query(sql.toString(), new ProductMapper(), id);
        if (products.size() == 0) return null;
        return products.get(0);
    }

    @Override
    public int getTotalItemsByCategoryCode(String code) {
        String sql = "SELECT COUNT(*) FROM product JOIN category ON product.categoryid = category.id WHERE category.code = ? AND product.status = 1";
        return count(sql, code);
    }

    @Override
    public int getTotalItemsByKeyword(String keyword) {
        String sql = "SELECT COUNT(*) FROM product WHERE (name LIKE ? OR description LIKE ?) AND status = 1";
        return count(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    @Override
    public int getTotalItemsByCategoryAndSupplierCodeAndPrice(String categoryCode, String supplierCode, long[] priceFilter) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM product P JOIN category C ON P.categoryid = C.id JOIN supplier S ON P.supplierid = S.id");
        sql.append(" WHERE P.status = 1 AND C.code = ? AND S.code = ? AND P.price * (100 - P.discount) / 100 >= ? and P.price * (100 - P.discount) / 100 <= ?");
        return count(sql.toString(), categoryCode, supplierCode, priceFilter[0], priceFilter[1]);
    }

    @Override
    public List<ProductModel> findAllByCategoryCodeAndPrice(String categoryCode, long[] priceFilter, IPageble pageble) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT P.*, I.imagelink, category.name AS cname FROM");
        sql.append(" product P join productgallery I on P.id = I.productid join category on P.categoryid = category.id");
        sql.append(" WHERE P.status = 1 and category.code = ? and P.price * (100 - P.discount) / 100 >= ? and P.price * (100 - P.discount) / 100 <= ?");

        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" GROUP BY P.id");
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
        } else if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" GROUP BY P.id");
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
        }
        return query(sql.toString(), new ProductMapper(), categoryCode, priceFilter[0], priceFilter[1]);
    }

    @Override
    public int getTotalItemsByCategoryCodeAndPrice(String categoryCode, long[] priceFilter) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM product P JOIN category C ON P.categoryid = C.id");
        sql.append(" WHERE P.status = 1 AND C.code = ? AND P.price * (100 - P.discount) / 100 >= ? and P.price * (100 - P.discount) / 100 <= ?");
        return count(sql.toString(), categoryCode, priceFilter[0], priceFilter[1]);
    }

    @Override
    public int getTotalItemsByStatus(int status) {
        String sql = "SELECT COUNT(*) FROM product WHERE status = ?";
        return count(sql, status);
    }

    @Override
    public List<ProductModel> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT P.*, I.imagelink, C.name AS cname, S.name as sname");
        sql.append(" FROM product P join productgallery I on P.id = I.productid join category C on P.categoryid = C.id join supplier S on P.supplierid = S.id");
        return query(sql.toString(), new ProductMapper());
    }

    @Override
    public Long insert(ProductModel productModel) {
        String sql = "INSERT INTO product(name, price, discount, description, status, categoryid, supplierid, createddate ,createdby ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, productModel.getName(), productModel.getPrice(), productModel.getDiscount(), productModel.getDescription(), productModel.getStatus(), productModel.getCategoryId(), productModel.getSupplierId(), productModel.getCreatedDate(), productModel.getCreatedBy());
    }

    @Override
    public boolean update(ProductModel productModel) {
        String sql = "UPDATE product SET name = ?, price = ?, discount = ?, description = ?, status = ?, categoryid = ?, supplierid = ?, modifieddate = ? ,modifiedby = ? WHERE id = ?";
        return update(sql, productModel.getName(), productModel.getPrice(), productModel.getDiscount(), productModel.getDescription(), productModel.getStatus(), productModel.getCategoryId(), productModel.getSupplierId(), productModel.getModifiedDate(), productModel.getModifiedBy(), productModel.getId());
    }

    @Override
    public int getTotalItemsByCategoryAndSupplierCode(String categoryCode, String supplierCode) {
        String sql = "SELECT COUNT(*) FROM product JOIN category ON product.categoryid = category.id JOIN supplier ON product.supplierid = supplier.id WHERE category.code = ? AND supplier.code = ? AND product.status = 1";
        return count(sql, categoryCode, supplierCode);
    }

    @Override
    public List<ProductModel> findAllByCategoryId(long categoryId) {
        String sql = "SELECT P.*, I.imagelink FROM product P join productgallery I on P.id = I.productid WHERE P.categoryid = ? AND P.status = 1";
        return query(sql, new ProductMapper(), categoryId);
    }

    @Override
    public List<ProductModel> findAllByKeyWord(String keyword, IPageble pageble) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT P.*, I.imagelink FROM");
        sql.append(" product P join productgallery I on P.id = I.productid");
        sql.append(" WHERE P.status = 1 and (P.name LIKE ? OR P.description LIKE ?)");

        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" GROUP BY P.id");
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
        } else if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" GROUP BY P.id");
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
        }
        return query(sql.toString(), new ProductMapper(), "%" + keyword + "%", "%" + keyword + "%");
    }

    @Override
    public List<ProductModel> findAllByCategoryAndSupplierCode(String category, String supplier, IPageble pageble) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT P.*, I.imagelink, category.name AS cname FROM");
        sql.append(" product P join productgallery I on P.id = I.productid join category on P.categoryid = category.id join supplier S on P.supplierid = S.id");
        sql.append(" WHERE P.status = 1 and category.code = ? and S.code = ?");

        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" GROUP BY P.id");
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
        } else if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" GROUP BY P.id");
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
        }
        return query(sql.toString(), new ProductMapper(), category, supplier);
    }

    @Override
    public List<ProductModel> findAllByCategoryAndSupplierCodeAndPrice(String categoryCode, String supplierCode, long[] priceFilter, IPageble pageble) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT P.*, I.imagelink, category.name AS cname FROM");
        sql.append(" product P join productgallery I on P.id = I.productid join category on P.categoryid = category.id join supplier S on P.supplierid = S.id");
        sql.append(" WHERE P.status = 1 and category.code = ? and S.code = ? and P.price * (100 - P.discount) / 100 >= ? and P.price * (100 - P.discount) / 100 <= ?");

        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" GROUP BY P.id");
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
        } else if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" GROUP BY P.id");
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
        }
        return query(sql.toString(), new ProductMapper(), categoryCode, supplierCode, priceFilter[0], priceFilter[1]);
    }

    @Override
    public List<ProductModel> findAllByCategoryCode(String code, IPageble pageble) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT P.*, I.imagelink, category.name AS cname FROM");
        sql.append(" product P join productgallery I on P.id = I.productid join category on P.categoryid = category.id");
        sql.append(" WHERE P.status = 1 and category.code = ?");

        if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" GROUP BY P.id");
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
        } else if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" GROUP BY P.id");
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
        }
        return query(sql.toString(), new ProductMapper(), code);
    }
}
