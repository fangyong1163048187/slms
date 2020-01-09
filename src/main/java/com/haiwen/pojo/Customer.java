package com.haiwen.pojo;


public class Customer {

  private long id;
  private String customernumber;
  private String customername;
  private String linkman;
  private String linkphone;
  private String linkaddress;
  private java.sql.Timestamp createtime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCustomernumber() {
    return customernumber;
  }

  public void setCustomernumber(String customernumber) {
    this.customernumber = customernumber;
  }


  public String getCustomername() {
    return customername;
  }

  public void setCustomername(String customername) {
    this.customername = customername;
  }


  public String getLinkman() {
    return linkman;
  }

  public void setLinkman(String linkman) {
    this.linkman = linkman;
  }


  public String getLinkphone() {
    return linkphone;
  }

  public void setLinkphone(String linkphone) {
    this.linkphone = linkphone;
  }


  public String getLinkaddress() {
    return linkaddress;
  }

  public void setLinkaddress(String linkaddress) {
    this.linkaddress = linkaddress;
  }


  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }

}
