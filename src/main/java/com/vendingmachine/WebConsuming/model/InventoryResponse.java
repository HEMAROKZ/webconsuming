package com.vendingmachine.WebConsuming.model;

public class InventoryResponse {
    private Inventory inventory;
    private String errorMessage;

    // Constructors, getters, and setters

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static InventoryResponse success(Inventory inventory) {
        InventoryResponse response = new InventoryResponse();
        response.setInventory(inventory);
        return response;
    }

    public static InventoryResponse error(String errorMessage) {
        InventoryResponse response = new InventoryResponse();
        response.setErrorMessage(errorMessage);
        return response;
    }
}

