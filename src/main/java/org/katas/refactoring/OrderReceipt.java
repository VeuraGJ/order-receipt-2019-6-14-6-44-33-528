package org.katas.refactoring;

import java.util.concurrent.atomic.AtomicReference;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order o;

    public OrderReceipt(Order o) {
        this.o = o;
    }

    public String printReceipt() {
        final double TAX_RATE = .10;
        final double TOTAL_SALES_TAX = getLineItemsTotalAmount()*TAX_RATE;
        final double TOTAL_AMOUNT = getLineItemsTotalAmount() + TOTAL_SALES_TAX;
        return String.format("======Printing Orders======\n"
                +"%s%s%sSales Tax\t%.1fTotal Amount\t%.1f",
                o.getCustomerName(),
                o.getCustomerAddress(),
                printItems(),
                TOTAL_SALES_TAX,
                TOTAL_AMOUNT);
    }
    public double getLineItemsTotalAmount(){
        return o.getLineItems()
                .stream()
                .mapToDouble(LineItem::getTotalAmount).sum();
    }

    private String printItems() {
        AtomicReference<String> lineItems = new AtomicReference<>("");
        o.getLineItems().stream()
                .forEach(lineItem -> lineItems.set(lineItems + String.format("%s\t%.1f\t%d\t%.1f\n",
                        lineItem.getDescription(),
                        lineItem.getPrice(),
                        lineItem.getQuantity(),
                        lineItem.getTotalAmount())));
        return lineItems.get();

    }
}