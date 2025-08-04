package dev.ankis.po.models;

public record PurchaseOrderLine(String id, Integer lineNumber, String itemNumber, Integer qty, String uom, Double amount) {
}
