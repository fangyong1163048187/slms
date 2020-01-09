package com.haiwen.pojo;


public class Product {

  private long id;
  private String productname;
  private String productnumber;
  private double productsalesprice;
  private double productbuyprice;
  private Stock stock;

  @Override
  public String toString() {
    return "Product{" +
            "id=" + id +
            ", productname='" + productname + '\'' +
            ", productnumber='" + productnumber + '\'' +
            ", productsalesprice=" + productsalesprice +
            ", productbuyprice=" + productbuyprice +
            ", stock=" + stock +
            '}';
  }

  public double getProductsalesprice() {
    return productsalesprice;
  }

  public void setProductsalesprice(double productsalesprice) {
    this.productsalesprice = productsalesprice;
  }

  public double getProductbuyprice() {
    return productbuyprice;
  }

  public void setProductbuyprice(double productbuyprice) {
    this.productbuyprice = productbuyprice;
  }

  public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getProductname() {
    return productname;
  }

  public void setProductname(String productname) {
    this.productname = productname;
  }


  public String getProductnumber() {
    return productnumber;
  }

  public void setProductnumber(String productnumber) {
    this.productnumber = productnumber;
  }

}
