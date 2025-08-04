```graphql 
query {
  purchaseOrderWithPONumber(purchaseOrderNumber: 125) {
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
```