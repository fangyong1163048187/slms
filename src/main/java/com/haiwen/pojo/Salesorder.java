package com.haiwen.pojo;


public class Salesorder {

  private long id;
  private String ordernumber;
  private long productid;
  private long productamount;
  private double productallprice;
  private long employeeid;
  private long customerid;
  private String customername;
  private java.sql.Timestamp createtime;
  private Product product;
  private Customer customer;
  private Employee employee;

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
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


  public long getCustomerid() {
    return customerid;
  }

  public void setCustomerid(long customerid) {
    this.customerid = customerid;
  }


  public String getCustomername() {
    return customername;
  }

  public void setCustomername(String customername) {
    this.customername = customername;
  }


  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }

}
