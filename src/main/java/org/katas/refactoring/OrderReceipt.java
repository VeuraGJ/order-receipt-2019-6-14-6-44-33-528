package org.katas.refactoring;

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

        // prints lineItems
        double totalSalesTax = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : o.getLineItems()) {
            printItem(output, lineItem);

            // calculate sales tax @ rate of 10%
            double taxRate = .10;
            double salesTax = lineItem.totalAmount() * taxRate;
            totalSalesTax += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalAmount += lineItem.totalAmount() + salesTax;
        }
        printSaleTax(output, totalSalesTax, "Sales Tax");

        // print total amount
        output.append("Total Amount").append('\t').append(totalAmount);
        return output.toString();
    }

    private void printSaleTax(StringBuilder output, double totalSalesTax, String s) {
        // prints the state tax
        output.append(s).append('\t').append(totalSalesTax);
    }

    private void printItem(StringBuilder output, LineItem lineItem) {
        output.append(lineItem.getDescription());
        output.append('\t');
        output.append(lineItem.getPrice());
        output.append('\t');
        output.append(lineItem.getQuantity());
        output.append('\t');
        output.append(lineItem.totalAmount());
        output.append('\n');
    }

    private void printCustomerInformation(StringBuilder output) {
        // print date, bill no, customer name
//        output.append("Date - " + order.getDate();
        output.append(o.getCustomerName());
        output.append(o.getCustomerAddress());
//        output.append(order.getCustomerLoyaltyNumber());
    }

    private void printHeader(StringBuilder output, String s) {
        // print headers
        output.append(s);
    }
}