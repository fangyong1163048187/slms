package com.haiwen.pojo;


public class Buyorder {

  private long id;
  private String ordernumber;
  private long productid;
  private long productamount;
  private double productallprice;
  private long employeeid;
  private long supplierid;
  private String suppliername;
  private java.sql.Timestamp createtime;
  private Product product;
  private Supplier supplier;
  private Employee employee;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getOrdernumber() {
    return ordernumber;
  }

  public void setOrdernumber(String ordernumber) {
    this.ordernumber = ordernumber;
  }


  public long getProductid() {
    return productid;
  }

  public void setProductid(long productid) {
    this.productid = productid;
  }


  public long getProductamount() {
    return productamount;
  }

  public void setProductamount(long productamount) {
    this.productamount = productamount;
  }


  public double getProductallprice() {
    return productallprice;
  }

  public void setProductallprice(double productallprice) {
    this.productallprice = productallprice;
  }


  public long getEmployeeid() {
    return employeeid;
  }

  public void setEmployeeid(long employeeid) {
    this.employeeid = employeeid;
  }


  public long getSupplierid() {
    return supplierid;
  }

  public void setSupplierid(long supplierid) {
    this.supplierid = supplierid;
  }


  public String getSuppliername() {
    return suppliername;
  }

  public void setSuppliername(String suppliername) {
    this.suppliername = suppliername;
  }


  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }

}
