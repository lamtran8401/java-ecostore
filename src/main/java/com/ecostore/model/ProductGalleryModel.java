package com.ecostore.model;

public class ProductGalleryModel extends AbstractModel {
    private String imageLink;
    private long productId;

    public ProductGalleryModel() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imagelink) {
        this.imageLink = imagelink;
    }
}
