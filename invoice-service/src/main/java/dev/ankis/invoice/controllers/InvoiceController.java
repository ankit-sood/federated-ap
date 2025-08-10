package dev.ankis.invoice.controllers;

import dev.ankis.invoice.models.Invoice;
import dev.ankis.invoice.models.InvoiceLine;
import dev.ankis.invoice.models.PurchaseOrder;
import dev.ankis.invoice.services.InvoiceService;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @EntityMapping("Invoice")
    public Invoice find(@Argument String invoiceNumber, DataFetchingEnvironment env) {
        Set<String> fields = getFields(env);
        return invoiceService.getInvoiceByInvNumber(invoiceNumber);
    }

    // Stub is only required to satisfy spring graphql
    @EntityMapping("PurchaseOrder")
    public PurchaseOrder resolvePurchaseOrder(@Argument int purchaseOrderNumber) {
        // Only return a reference with the key field set
        return null;
    }

    // Stub is only required to satisfy spring graphql
    @SchemaMapping(typeName = "Invoice", field = "purchaseOrder")
    public PurchaseOrder getPurchaseOrder(Invoice invoice) {
        // Call purchase-order-service using invoice.getPurchaseOrderNumber()
        // Return the PurchaseOrder object
        return new PurchaseOrder("PO1", 10000D, invoice.purchaseOrderNumber(), 111, null);
    }

    @QueryMapping("invoiceByInvoiceNumber")
    public Invoice findInvoiceByInvoiceNumber(@Argument String invoiceNumber, DataFetchingEnvironment env) {
        Set<String> fields = getFields(env);
        return invoiceService.getInvoiceByInvNumber(invoiceNumber);
    }

    @SchemaMapping(typeName = "Invoice", field="invoiceLines")
    public List<InvoiceLine> findInvoiceLines(Invoice invoice, DataFetchingEnvironment env) {
        Set<String> fields = getFields(env);
        return invoiceService.getInvoiceLinesByInvNumber(invoice.id());
    }

    private Set<String> getFields(DataFetchingEnvironment env){
        return env.getSelectionSet().getFields().stream()
                .map(SelectedField::getName)
                .collect(Collectors.toSet());
    }
}
