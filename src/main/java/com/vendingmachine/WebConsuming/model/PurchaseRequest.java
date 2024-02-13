package com.vendingmachine.WebConsuming.model;


import com.vendingmachine.WebConsuming.model.purchaseproduct.*;

public class PurchaseRequest {
    private MultiplePurchaseDTO multiplePurchaseDTO;
    private DenominationConfig denominationConfig;
    private int billingCounter;

    public MultiplePurchaseDTO getMultiplePurchaseDTO() {
        return multiplePurchaseDTO;
    }

    public void setMultiplePurchaseDTO(MultiplePurchaseDTO multiplePurchaseDTO) {
        this.multiplePurchaseDTO = multiplePurchaseDTO;
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
                "multiplePurchaseDTO=" + multiplePurchaseDTO +
                ", denominationConfig=" + denominationConfig +
                ", billingCounter=" + billingCounter +
                '}';
    }
}
