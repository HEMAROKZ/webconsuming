package com.vendingmachine.WebConsuming.model;


import com.vendingmachine.WebConsuming.model.purchaseproduct.*;

public class PurchaseRequest {
    private DenominationConfig denominationConfig;
    private int billingCounter;

    private PurchaseDTO purchaseDTO;



    public PurchaseDTO getPurchaseDTO() {
        return purchaseDTO;
    }

    public void setPurchaseDTO(PurchaseDTO purchaseDTO) {
        this.purchaseDTO = purchaseDTO;
    }

    public DenominationConfig getDenominationConfig() {
        return denominationConfig;
    }

    public void setDenominationConfig(DenominationConfig denominationConfig) {
        this.denominationConfig = denominationConfig;
    }

    public int getBillingCounter() {
        return billingCounter;
    }

    public void setBillingCounter(int billingCounter) {
        this.billingCounter = billingCounter;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "multiplePurchaseDTO=" + purchaseDTO +
                ", denominationConfig=" + denominationConfig +
                ", billingCounter=" + billingCounter +
                '}';
    }
}
