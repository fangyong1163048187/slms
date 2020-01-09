package com.haiwen.service;

import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.Salesorder;

import java.sql.SQLException;
import java.util.Map;

public interface SalesOrderService {
    /**
     * 查询订货信息
     * @param orderNumber 订单编号
     * @param employeeName 经办人姓名
     * @param pageSize 每页查询记录数
     * @param pageNumber 当前页
     * @return 查询到的相关信息
     */
    PageInfo selectSalesOrder(String orderNumber, String employeeName, Integer pageSize, Integer pageNumber);

    /**
     * 删除一个订单信息,就是取消订单
     * @param orderNumber 订单编号
     * @return
     */
    Integer deleteOneSalesOrder(String orderNumber)throws SQLException;

    /**
     * 清空数据
     * @return
     */
    Integer deleteAll() throws SQLException;

    /**
     * 查找所有顾客、商品、经办人
     * @return
     */
    Map<String,Object> selectCusProEmp();

    /**
     * 判断库存是否充足
     * @param amount
     * @param number
     * @return
     */
    Boolean selectJudgeStockAmount(Long amount,String number);

    /**
     * 插入订单表
     * @param customerNumber 顾客编号
     * @param productNumber 商品编号
     * @param employeeNumber 员工编号
     * @param productAmount 商品数量
     * @return
     */
    String insertSalesOrder( String customerNumber,String productNumber,String employeeNumber,Long productAmount) throws SQLException;

    /**
     * 修改
     * @param customerNumber
     * @param productNumber
     * @param employeeNumber
     * @param productAmount
     * @param date
     * @param orderNumber
     * @return
     */
    Integer updateSalesOrder(String customerNumber,String productNumber,String employeeNumber,Long productAmount,String date,String orderNumber)throws Exception;

}
