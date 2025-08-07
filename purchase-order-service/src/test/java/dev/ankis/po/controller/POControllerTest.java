package dev.ankis.po.controller;

import dev.ankis.po.models.PurchaseOrder;
import dev.ankis.po.services.PurchaseOrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@SuppressWarnings("removal")
@GraphQlTest(POController.class)
public class POControllerTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private PurchaseOrderService purchaseOrderService;

    @Test
    void shouldGetFirstBook() {
        Mockito.when(purchaseOrderService.getPoDetailsByPoNumber(125l)).thenReturn(List.of(new PurchaseOrder("PO3", 25000D, 125l, 111, 123l)));
        Mockito.when(purchaseOrderService.getPoDetailsByPoNumber(123l)).thenReturn(List.of(new PurchaseOrder("PO1", 10000D, 123l, 111, null)));
        this.graphQlTester
                .documentName("purchaseOrder")
                .variable("purchaseOrderNumber", "125")
                .execute()
                .path("purchaseOrderWithPONumber")
                .matchesJson("""
                                   [{
                                     "purchaseOrderNumber": 125,
                                     "totalAmount": 25000.0,
                                     "altPO": {
                                       "purchaseOrderNumber": 123,
                                       "totalAmount": 10000.0
                                     },
                                     "purchaseOrderLines": [
                                       {
                                         "lineNumber": 1,
                                         "itemNumber": "I12",
                                         "qty": 25,
                                         "uom": "EACH",
                                         "amount": 100.0
                                       }
                                     ]
                                   }]
                        """);
    }
}
