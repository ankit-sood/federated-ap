package dev.ankis.po.controller;

import dev.ankis.po.models.PurchaseOrder;
import dev.ankis.po.services.PurchaseOrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class POController {
    private final PurchaseOrderService purchaseOrderService;

    public POController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @QueryMapping("purchaseOrderByPONumber")
    public PurchaseOrder findPurchaseOrderByPONumber(@Argument Long purchaseOrderNumber) {
        return purchaseOrderService.getByPoNumber(purchaseOrderNumber);
    }

    @QueryMapping("purchaseOrderWithPONumber")
    public List<PurchaseOrder> getPurchaseOrderByPONumber(@Argument Long purchaseOrderNumber) {
        return purchaseOrderService.getPoDetailsByPoNumber(purchaseOrderNumber);
    }
}
