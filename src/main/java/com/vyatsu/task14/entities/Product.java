package com.vyatsu.task14.entities;

public class Product {
    private Long id;
    private String title;
    private int price;
    private int viewCount;
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
	
    public int getViewCount() {
        return viewCount;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Product() {
    }

    public Product(Long id, String title, int price, int viewCount, String category) {
        this.id = id;
        this.title = title;
        this.price = price;
	    this.viewCount = viewCount;
        this.category = category;
    }
}
