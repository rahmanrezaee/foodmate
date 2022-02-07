package com.development.footmat.models;

import java.io.Serializable;

public class ItemModel implements Serializable {

    private String title;
    private String description;
    private String imgIcon;

    public ItemModel() {
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

    public String getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(String imgIcon) {
        this.imgIcon = imgIcon;
    }

    public ItemModel(String title, String description, String imgIcon) {
        this.title = title;
        this.description = description;
        this.imgIcon = imgIcon;
    }
}
