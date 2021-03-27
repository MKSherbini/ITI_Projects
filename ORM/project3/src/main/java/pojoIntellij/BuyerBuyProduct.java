package com.sample;


public class BuyerBuyProduct {

  private long buyerId;
  private long productId;
  private java.sql.Date paymentDate;
  private double amount;
  private long quantity;


  public long getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(long buyerId) {
    this.buyerId = buyerId;
  }


  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }


  public java.sql.Date getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(java.sql.Date paymentDate) {
    this.paymentDate = paymentDate;
  }


  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }


  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }

}
