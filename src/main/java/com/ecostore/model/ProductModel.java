package com.ecostore.model;

import java.util.ArrayList;
import java.util.List;

public class ProductModel extends AbstractModel {
    private String name;
    private Long price;
    private int discount;
    private String description;
    private int status;
    private CategoryModel category;
    private SupplierModel supplierModel;
    private long categoryId;
    private long supplierId;
    private List<String> images;
    private List<UploadFileModel> uploadFiles;

    public ProductModel() {
    }

    public List<UploadFileModel> getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(List<UploadFileModel> uploadFiles) {
        this.uploadFiles = uploadFiles;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public SupplierModel getSupplierModel() {
        return supplierModel;
    }

    public void setSupplierModel(SupplierModel supplier) {
        this.supplierModel = supplier;
    }

    public void addImage(String path) {
        if(this.images == null) this.images = new ArrayList<>();
        this.images.add(path);
    }
}
