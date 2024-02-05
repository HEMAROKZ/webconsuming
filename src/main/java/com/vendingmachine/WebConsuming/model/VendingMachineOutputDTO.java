package com.vendingmachine.WebConsuming.model;


public class VendingMachineOutputDTO {

    private final String item;
    private final int price;
    private final int balance;

    public VendingMachineOutputDTO(String item, int price, int balance) {
        this.item = item;
        this.price = price;
        this.balance = balance;
    }

    public VendingMachineOutputDTO(VendingMachineOutputDTOBuilder vendingMachineOutputDTOBuilder) {
        this.item = vendingMachineOutputDTOBuilder.item;
        this.price = vendingMachineOutputDTOBuilder.price;
        this.balance = vendingMachineOutputDTOBuilder.balance;
    }

    public static VendingMachineOutputDTOBuilder builder(){
        return new VendingMachineOutputDTOBuilder();
    }

    public String getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public int getBalance() {
        return balance;
    }

    public static class VendingMachineOutputDTOBuilder {
        private String item;
        private int price;
        private int balance;

        public VendingMachineOutputDTOBuilder() {
        }

        public VendingMachineOutputDTOBuilder balance(int balance) {
            this.balance = balance;
            return this;
        }

        public VendingMachineOutputDTOBuilder price(int price) {
            this.price = price;
            return this;
        }
        public VendingMachineOutputDTOBuilder item(String item) {
            this.item = item;
            return this;
        }

        public VendingMachineOutputDTO build() {
            return new VendingMachineOutputDTO(this);
        }

    }

}
