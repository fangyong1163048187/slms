package com.haiwen.pojo;


public class Stock {

  private long id;
  private long productid;
  private long stockamount;
  private long salesamount;
  private java.sql.Timestamp createtime;

  @Override
  public String toString() {
    return "Stock{" +
            "id=" + id +
            ", productid=" + productid +
            ", stockamount=" + stockamount +
            ", salesamount=" + salesamount +
            ", createtime=" + createtime +
            '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getProductid() {
    return productid;
  }

  public void setProductid(long productid) {
    this.productid = productid;
  }


  public long getStockamount() {
    return stockamount;
  }

  public void setStockamount(long stockamount) {
    this.stockamount = stockamount;
  }


  public long getSalesamount() {
    return salesamount;
  }

  public void setSalesamount(long salesamount) {
    this.salesamount = salesamount;
  }

  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }

}
