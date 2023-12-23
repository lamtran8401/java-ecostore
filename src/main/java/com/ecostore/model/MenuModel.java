package com.ecostore.model;

public class MenuModel extends AbstractModel {
    private String name;
    private String link;
    private int status;
    private MenuTypeModel menuType;
    private long menuTypeId;

    public long getMenuTypeId() {
        return menuTypeId;
    }

    public void setMenuTypeId(long menuTypeId) {
        this.menuTypeId = menuTypeId;
    }

    public MenuModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MenuTypeModel getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuTypeModel menuType) {
        this.menuType = menuType;
    }

}
