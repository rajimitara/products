package com.amazon.products.ds;

public class UpdateProduct {

    public double currentPrice; //why not float
    public String timestamp;

    public UpdateProduct(double currentPrice, String timestamp) {
        this.currentPrice = currentPrice;
        this.timestamp = timestamp;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
