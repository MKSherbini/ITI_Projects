package com.sample;


public class Product {

  private long id;
  private String name;
  private String description;
  private String manufacturingName;
  private java.sql.Date manufacturingDate;
  private java.sql.Date expirationDate;
  private long sellerId;
  private long quantity;
  private java.sql.Date offeredDate;
  private java.sql.Date finishDate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getManufacturingName() {
    return manufacturingName;
  }

  public void setManufacturingName(String manufacturingName) {
    this.manufacturingName = manufacturingName;
  }


  public java.sql.Date getManufacturingDate() {
    return manufacturingDate;
  }

  public void setManufacturingDate(java.sql.Date manufacturingDate) {
    this.manufacturingDate = manufacturingDate;
  }


  public java.sql.Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(java.sql.Date expirationDate) {
    this.expirationDate = expirationDate;
  }


  public long getSellerId() {
    return sellerId;
  }

  public void setSellerId(long sellerId) {
    this.sellerId = sellerId;
  }


  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }


  public java.sql.Date getOfferedDate() {
    return offeredDate;
  }

  public void setOfferedDate(java.sql.Date offeredDate) {
    this.offeredDate = offeredDate;
  }


  public java.sql.Date getFinishDate() {
    return finishDate;
  }

  public void setFinishDate(java.sql.Date finishDate) {
    this.finishDate = finishDate;
  }

}
