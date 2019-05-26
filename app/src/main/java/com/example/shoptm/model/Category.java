package com.example.shoptm.model;

public class Category {
    private String categoryName;
    private String categoryIconLink;

    public Category() {
    }

    public Category(String categoryName, String categoryIconLink) {
        this.categoryName = categoryName;
        this.categoryIconLink = categoryIconLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIconLink() {
        return categoryIconLink;
    }

    public void setCategoryIconLink(String categoryIconLink) {
        this.categoryIconLink = categoryIconLink;
    }
}
