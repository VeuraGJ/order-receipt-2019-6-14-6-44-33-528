package org.katas.refactoring;

import java.util.List;

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
        StringBuilder output = new StringBuilder();
        printHeader(output, "======Printing Orders======\n");
        printCustomerInformation(output);
        printItems(output, o.getLineItems());
        double totalSalesTax = calculateLineItemsSalesTaxRateOf10precent(o.getLineItems());
        double totalAmount = calculateLineItemsTotalAmount(o.getLineItems());
        printSaleTax(output, totalSalesTax, "Sales Tax");
        printTotalAmount(output, totalAmount, "Total Amount");
        return output.toString();
    }
    private double calculateLineItemsSalesTaxRateOf10precent(List<LineItem> lineItems){
        double taxRate = .10;
        return lineItems.stream()
                .mapToDouble(LineItem::totalAmount)
                .map(item -> item * taxRate)
                .sum();
    }
    private double calculateLineItemsTotalAmount(List<LineItem> lineItems){
        double taxRate = .10;
        return lineItems.stream()
                .mapToDouble(LineItem::totalAmount)
                .map(item -> item * taxRate + item)
                .sum();
    }
    private void printTotalAmount(StringBuilder output, double totalAmount, String s) {
        output.append(s).append('\t').append(totalAmount);
    }

    private void printSaleTax(StringBuilder output, double totalSalesTax, String s) {
        output.append(s).append('\t').append(totalSalesTax);
    }

    private void printItems(StringBuilder output, List<LineItem> lineItems) {
        lineItems.stream().forEach(lineItem -> {
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalAmount());
            output.append('\n');
        });
    }

    private void printCustomerInformation(StringBuilder output) {
        output.append(o.getCustomerName());
        output.append(o.getCustomerAddress());
    }

    private void printHeader(StringBuilder output, String s) {
        output.append(s);
    }
}