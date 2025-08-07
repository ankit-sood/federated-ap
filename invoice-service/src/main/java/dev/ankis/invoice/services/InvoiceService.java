package dev.ankis.invoice.services;

import dev.ankis.invoice.models.Invoice;
import dev.ankis.invoice.models.InvoiceLine;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {
    private static final Map<String, Invoice> INVOICES = new HashMap<>();
    private static final Map<String, List<InvoiceLine>> INVOICE_LINES = new HashMap<>();

    public InvoiceService() {
        INVOICES.put("INV-11", new Invoice("INV-11", "INV-11", 111, 15000.0F, 202, 25, 125l));
        INVOICE_LINES.put("INV-11", List.of(new InvoiceLine("INV-11", 1, "I12", 25, 600.0F)));

        INVOICES.put("INV-12", new Invoice("INV-12", "INV-12", 111, 45000.0F, 202, 5, 125l));
        INVOICE_LINES.put("INV-12", List.of(new InvoiceLine("INV-12", 1, "I12", 5, 9000.0F)));
    }

    public Invoice getInvoiceByInvNumber(String invoiceNumber) {
        return INVOICES.get(invoiceNumber);
    }

    public List<InvoiceLine> getInvoiceLinesByInvNumber(String invoiceId) {
        return INVOICE_LINES.get(invoiceId);
    }
}
