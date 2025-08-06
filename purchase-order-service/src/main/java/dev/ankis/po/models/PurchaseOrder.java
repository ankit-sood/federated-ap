package dev.ankis.po.models;

import java.util.List;

public record PurchaseOrder(String id,
                            Double totalAmount,
                            Long purchaseOrderNumber,
                            Integer vendorNumber,
                            Long altPurchaseOrderNumber) {

}
