package com.ecostore.model;

public class PaymentModel extends AbstractModel {
    private String name;
    private int status;

    public PaymentModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
