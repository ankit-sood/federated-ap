package dev.ankis.po.controller;

import dev.ankis.po.exceptions.PurchaseOrderNotFoundException;
import dev.ankis.po.models.PurchaseOrder;
import dev.ankis.po.models.PurchaseOrderLine;
import dev.ankis.po.services.PurchaseOrderService;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
public class POController {
    private final PurchaseOrderService purchaseOrderService;

    public POController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @EntityMapping("PurchaseOrder")
    public PurchaseOrder find(@Argument Long purchaseOrderNumber) {
        List<PurchaseOrder> purchaseOrders = purchaseOrderService.getPoDetailsByPoNumber(purchaseOrderNumber);
        return purchaseOrders.stream().findFirst().orElseThrow(() -> new PurchaseOrderNotFoundException("Purchase order not found"));
    }


    @QueryMapping("purchaseOrderWithPONumber")
    public List<PurchaseOrder> findPurchaseOrderByPONumber(@Argument Long purchaseOrderNumber) {
        return purchaseOrderService.getPoDetailsByPoNumber(purchaseOrderNumber);
    }

    @SchemaMapping(typeName = "PurchaseOrder", field="purchaseOrderLines")
    public List<PurchaseOrderLine> findPurchaseOrderLines(PurchaseOrder purchaseOrder) {
        List<PurchaseOrderLine> purchaseOrderLines = List.of(new PurchaseOrderLine(purchaseOrder.id(), 1, "I12", 25, "EACH", 100D));
        return purchaseOrderLines;
    }

    @SchemaMapping(typeName = "PurchaseOrder", field="altPO")
    public PurchaseOrder findAlternatePO(PurchaseOrder purchaseOrder) {
        if(purchaseOrder.altPurchaseOrderNumber() != null) {
            List<PurchaseOrder> altPOList = purchaseOrderService.getPoDetailsByPoNumber(purchaseOrder.altPurchaseOrderNumber());
            return altPOList.get(0);
        }
        return null;
    }
}
