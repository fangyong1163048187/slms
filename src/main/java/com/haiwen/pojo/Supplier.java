package com.haiwen.pojo;


public class Supplier {

  private long id;
  private String suppliernumber;
  private String suppliername;
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

  public String getSuppliernumber() {
    return suppliernumber;
  }

  public void setSuppliernumber(String suppliernumber) {
    this.suppliernumber = suppliernumber;
  }

  public String getSuppliername() {
    return suppliername;
  }

  public void setSuppliername(String suppliername) {
    this.suppliername = suppliername;
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
