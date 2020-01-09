package com.haiwen.pojo;

import java.util.List;

/**
 * 该类用来分页查询时存放分页数据
 */
public class PageInfo {
    private int pageNumber;//当前页数
    private int pageSize;//每页存放的个数
    private int pageStart;//从第几个开始查找
    private int pageTotal;//总页数
    private int pageCountOfEmployee;//查找到的员工人数
    private List<?> list;//查找到的每页要存放的对象
    private String employeeNumber;//存储员工编号
    private String employeeName;//存储员工名字
    private String productNumber;//存储商品编号
    private String productName;//存储商品名字
    private int pageCountOfProduct;//查找到的商品数
    private String customerNumber;//存储客户编号
    private String customerName;//存储客户名字
    private int pageCountOfCustomer;//查找到的客户数
    private String supplierNumber;//存储供应商编号
    private String supplierName;//存储供应商名字
    private int pageCountOfSupplier;//查找到的供应商数
    private String salesOrderNumber;//存储订单表编号
    private String empName;//存储经办人名字
    private int pageCountOfSalesOrder;//查找到的订单表数
    private String username;//存储用户名
    private Integer role;//存储用户角色
    private int pageCountOfUser;//查找到的用户总人数

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public int getPageCountOfUser() {
        return pageCountOfUser;
    }

    public void setPageCountOfUser(int pageCountOfUser) {
        this.pageCountOfUser = pageCountOfUser;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getPageCountOfSalesOrder() {
        return pageCountOfSalesOrder;
    }

    public void setPageCountOfSalesOrder(int pageCountOfSalesOrder) {
        this.pageCountOfSalesOrder = pageCountOfSalesOrder;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getPageCountOfSupplier() {
        return pageCountOfSupplier;
    }

    public void setPageCountOfSupplier(int pageCountOfSupplier) {
        this.pageCountOfSupplier = pageCountOfSupplier;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPageCountOfCustomer() {
        return pageCountOfCustomer;
    }

    public void setPageCountOfCustomer(int pageCountOfCustomer) {
        this.pageCountOfCustomer = pageCountOfCustomer;
    }

    public int getPageCountOfProduct() {
        return pageCountOfProduct;
    }

    public void setPageCountOfProduct(int pageCountOfProduct) {
        this.pageCountOfProduct = pageCountOfProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public int getPageCountOfEmployee() {
        return pageCountOfEmployee;
    }

    public void setPageCountOfEmployee(int pageCountOfEmployee) {
        this.pageCountOfEmployee = pageCountOfEmployee;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}