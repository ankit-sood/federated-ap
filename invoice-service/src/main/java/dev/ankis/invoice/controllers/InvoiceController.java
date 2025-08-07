package dev.ankis.invoice.controllers;

import dev.ankis.invoice.models.Invoice;
import dev.ankis.invoice.models.InvoiceLine;
import dev.ankis.invoice.services.InvoiceService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @QueryMapping("invoice")
    public Invoice findInvoiceByInvoiceNumber(@Argument String invoiceNumber) {
        return invoiceService.getInvoiceByInvNumber(invoiceNumber);
    }

    @SchemaMapping(typeName = "Invoice", field="invoiceLines")
    public List<InvoiceLine> findInvoiceLines(Invoice invoice) {
        return invoiceService.getInvoiceLinesByInvNumber(invoice.id());
    }
}
