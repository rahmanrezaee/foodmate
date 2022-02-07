package com.development.footmat.models;

public class CategoryModel {

    private String title;
    private String items;
    private String imgIcon;

    public CategoryModel() {
    }

    public CategoryModel(String title, String items, String imgIcon) {
        this.title = title;
        this.items = items;
        this.imgIcon = imgIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(String imgIcon) {
        this.imgIcon = imgIcon;
    }
}
