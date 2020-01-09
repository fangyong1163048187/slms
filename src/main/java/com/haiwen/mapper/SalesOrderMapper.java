package com.haiwen.mapper;

import com.haiwen.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface SalesOrderMapper {
    /**
     * 通过订单编号和经办人姓名查询相关订单
     * @param orderNumber
     * @param employeeName
     * @param pageInfo
     * @return
     */
    List<Salesorder> selectBySalesOrderNumAndPsd(@Param("orderNumber")String orderNumber, @Param("employeeName")String employeeName, @Param("pageInfo") PageInfo pageInfo);

    /**
     * 查询符合查询条件的订单表总记录数
     * @param orderNumber
     * @param employeeName
     * @return
     */
    Integer selectCountOfSalesOrder(@Param("orderNumber")String orderNumber, @Param("employeeName")String employeeName);

    /**
     * 删除一个订单信息
     * @param orderNumber 订单编号
     * @return
     */
    Integer deleteOneSalesOrder(@Param("orderNumber")String orderNumber);

    /**
     * 清空数据
     * @return
     */
    Integer deleteAll();

    /**
     * 查找所有客户
     * @return
     */
    List<Customer> selectAllCustomer();

    /**
     * 查找所有商品
     * @return
     */
    List<Product> selectAllProduct();

    /**
     * 查找所有经办人
     * @return
     */
    List<Employee> selectAllEmployee();

    /**
     * 查找库存表
     * @return
     */
    Long selectStockAmount(@Param("number") String number);

    /**
     * 插入订单表
     * @param salesorder
     * @return
     */
    Integer insertSalesOrder(Salesorder salesorder);

    /**
     * 修改库存表
     * @param salesorder
     * @return
     */
    Integer updateStock(Salesorder salesorder);

    /**
     * 通过顾客编号查找顾客ID和顾客名字
     * @param customerNumber
     * @return
     */
    Customer selectCustomer(String customerNumber);

    /**
     * 通过商品编号查找商品ID和商品单价
     * @param productNumber
     * @return
     */
    Product selectProduct(String productNumber);

    /**
     * 通过员工编号查找员工ID
     * @param employeeNumber
     * @return
     */
    Long selectEmployeeId(String employeeNumber);
    /**
     * 通过订单表ID查找订单表编号
     * @param salesOrderId
     * @return
     */
    Salesorder selectSalesOrderNumber(Long salesOrderId);

    /**
     * 修改订货表
     * @param salesorder
     * @return
     */
    Integer updateSalesOrder(Salesorder salesorder);

    /**
     * 通过订单编号查询相关订单
     * @param salesOrderNumber
     * @return
     */
    Salesorder selectSalesOrderByNumber(String salesOrderNumber);
}
