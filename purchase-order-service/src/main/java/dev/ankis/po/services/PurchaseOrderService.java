package dev.ankis.po.services;

import dev.ankis.po.models.PurchaseOrder;
import dev.ankis.po.models.PurchaseOrderLine;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PurchaseOrderService {
    private static final Map<Long, PurchaseOrder> PURCHASE_ORDERS = new HashMap<>();

    public PurchaseOrderService() {
        PURCHASE_ORDERS.put(123l, new PurchaseOrder("PO1", 10000D, 123l, 111, null));
        PURCHASE_ORDERS.put(124l, new PurchaseOrder("PO2", 20000D, 124l, 111, null));
        PURCHASE_ORDERS.put(125l, new PurchaseOrder("PO3", 25000D, 125l, 111, 123l));
        PURCHASE_ORDERS.put(126l, new PurchaseOrder("PO4", 30000D, 126l, 111, 124l));
        PURCHASE_ORDERS.put(127l, new PurchaseOrder("PO5", 80000D, 127l, 111, null));
        PURCHASE_ORDERS.put(128l, new PurchaseOrder("PO6", 90000D, 128l, 111, null));
    }

    public List<PurchaseOrder> getPoDetailsByPoNumber(Long purchaseOrderNumber) {
        PurchaseOrder primaryPurchaseOrder = PURCHASE_ORDERS.get(purchaseOrderNumber);
        return primaryPurchaseOrder!= null ? List.of(primaryPurchaseOrder) : List.of();
    }
}
