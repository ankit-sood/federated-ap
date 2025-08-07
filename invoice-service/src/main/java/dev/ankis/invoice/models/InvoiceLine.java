package dev.ankis.invoice.models;

public record InvoiceLine(String id,
                          Integer lineNumber,
                          String itemNumber,
                          Integer qty,
                          Float amount) {
    public InvoiceLine {
        if(id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if(lineNumber == null) {
            throw new IllegalArgumentException("lineNumber cannot be null");
        }
        if(itemNumber == null) {
            throw new IllegalArgumentException("itemNumber cannot be null");
        }
        if(qty == null || qty <= 0) {
            throw new IllegalArgumentException("qty cannot be negative or null");
        }
        if(amount == null || qty.doubleValue() == 0 ) {
            throw new IllegalArgumentException("amount cannot be zero or null");
        }
    }
}
