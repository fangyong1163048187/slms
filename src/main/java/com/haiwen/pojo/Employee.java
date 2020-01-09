package com.haiwen.pojo;


import java.sql.Date;

public class Employee {

  private long id;
  private String employeenumber;
  private String employeename;
  private String employeesex;
  private Date employeebirthday;
  private String employeephone;
  private String employeeemail;
  private String employeeaddress;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getEmployeenumber() {
    return employeenumber;
  }

  public void setEmployeenumber(String employeenumber) {
    this.employeenumber = employeenumber;
  }


  public String getEmployeename() {
    return employeename;
  }

  public void setEmployeename(String employeename) {
    this.employeename = employeename;
  }


  public String getEmployeesex() {
    return employeesex;
  }

  public void setEmployeesex(String employeesex) {
    this.employeesex = employeesex;
  }


  public java.sql.Date getEmployeebirthday() {
    return employeebirthday;
  }

  public void setEmployeebirthday(java.sql.Date employeebirthday) {
    this.employeebirthday = employeebirthday;
  }


  public String getEmployeephone() {
    return employeephone;
  }

  public void setEmployeephone(String employeephone) {
    this.employeephone = employeephone;
  }


  public String getEmployeeemail() {
    return employeeemail;
  }

  public void setEmployeeemail(String employeeemail) {
    this.employeeemail = employeeemail;
  }


  public String getEmployeeaddress() {
    return employeeaddress;
  }

  public void setEmployeeaddress(String employeeaddress) {
    this.employeeaddress = employeeaddress;
  }

}
