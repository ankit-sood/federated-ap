# federated-ap
Repository to explain the Federated Graph.

**Query:**
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

** References **
- [GraphQL Maturity](https://dev.to/sandipd/from-basics-to-supergraph-a-practical-guide-for-your-graphql-adoption-journey-5cff)
- [Spring for graphQL](https://spring.io/guides/gs/graphql-server)