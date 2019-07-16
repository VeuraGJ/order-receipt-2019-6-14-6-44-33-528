package org.katas.refactoring;

public class LineItem {
    private String desciption;
    private double price;
    private int quantity;

    public LineItem(String desciption, double price, int quantity) {
        this.desciption = desciption;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDescription() {
        return desciption;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    double getTotalAmount() {
        return price * quantity;
    }
}