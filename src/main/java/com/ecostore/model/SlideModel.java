package com.ecostore.model;

public class SlideModel extends AbstractModel {
    private String title;
    private String description;
    private String imageLink;
    private int displayorder;
    private int status;
    private UploadFileModel uploadFile;

    public UploadFileModel getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadFileModel uploadFile) {
        this.uploadFile = uploadFile;
    }

    public SlideModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(int displayorder) {
        this.displayorder = displayorder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
