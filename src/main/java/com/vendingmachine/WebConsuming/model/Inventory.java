package com.vendingmachine.WebConsuming.model;

public class Inventory {

    public int productId;

    public String name;

    public int productPrice;

    public int productInventoryCount;


    public Inventory() {
    }

    public Inventory(int productId, String name, int productPrice, int productInventoryCount) {
        this.productId = productId;
        this.name = name;
        this.productPrice = productPrice;
        this.productInventoryCount = productInventoryCount;

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductInventoryCount() {
        return productInventoryCount;
    }

    public void setProductInventoryCount(int productInventoryCount) {
        this.productInventoryCount = productInventoryCount;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", productPrice=" + productPrice +
                ", productInventoryCount=" + productInventoryCount +
                '}';
    }
}
