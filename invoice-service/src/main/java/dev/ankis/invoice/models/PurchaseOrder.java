package dev.ankis.invoice.models;

public record PurchaseOrder(String id,
                            Double totalAmount,
                            Long purchaseOrderNumber,
                            Integer vendorNumber,
                            Long altPurchaseOrderNumber) {

}
