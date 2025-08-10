# federated-ap
Repository to explain the Federated Graph.

```graphql
query {
  invoiceByInvoiceNumber(invoiceNumber: "INV-11") {
    invoiceNumber
    invoiceLines {
      id
      lineNumber
      itemNumber
      qty
      amount
    }
    purchaseOrder {
      id
      vendorNumber
      purchaseOrderNumber
      totalAmount
      purchaseOrderLines {
        lineNumber
        itemNumber
        qty
        uom
        amount
      }
    }
  }
}
```