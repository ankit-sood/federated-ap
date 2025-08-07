package dev.ankis.invoice.models;

public record Invoice(String id,
                      String invoiceNumber,
                      Integer vendorNumber,
                      Float totalAmount,
                      Integer destNumber,
                      Integer totalQty,
                      Long purchaseOrderNumber) {
    public Invoice {
        if(id == null) {
            throw new IllegalArgumentException("Invoice id cannot be null");
        }
        if(invoiceNumber == null) {
            throw new IllegalArgumentException("Invoice number cannot be null");
        }
        if(vendorNumber == null) {
            throw new IllegalArgumentException("Vendor number cannot be null");
        }
        if(totalAmount == null || totalAmount.doubleValue() == 0) {
            throw new IllegalArgumentException("Total amount cannot be zero or null");
        }
        if(destNumber == null || destNumber.doubleValue() <= 0) {
            throw new IllegalArgumentException("Destination number cannot be negative, zero or null");
        }
        if(totalQty == null || totalQty.doubleValue() <= 0) {
            throw new IllegalArgumentException("Total quantity cannot be negative, zero or null");
        }
        if(purchaseOrderNumber == null || purchaseOrderNumber.doubleValue() <= 0) {
            throw new IllegalArgumentException("Purchase order number cannot be negative, zero or null");
        }
    }
}
