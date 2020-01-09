package com.haiwen.service;

import com.haiwen.pojo.PageInfo;

import java.sql.SQLException;
import java.util.Map;

public interface BuyOrderService {
    /**
     * 查询订货信息
     * @param orderNumber 订单编号
     * @param employeeName 经办人姓名
     * @param pageSize 每页查询记录数
     * @param pageNumber 当前页
     * @return 查询到的相关信息
     */
    PageInfo selectBuyOrder(String orderNumber, String employeeName, Integer pageSize, Integer pageNumber);
    /**
     * 删除一个订单信息,就是取消订单
     * @param orderNumber 订单编号
     * @return
     */
    Integer deleteOneBuyOrder(String orderNumber)throws SQLException;
    /**
     * 查找所有顾客、商品、经办人
     * @return
     */
    Map<String,Object> selectSupProEmp();

    /**
     * 插入订单表
     * @param supplierNumber 供应商编号
     * @param productNumber 商品编号
     * @param employeeNumber 员工编号
     * @param productAmount 商品数量
     * @return
     */
    String insertBuyOrder( String supplierNumber,String productNumber,String employeeNumber,Long productAmount) throws SQLException;
    /**
     * 修改
     * @param supplierNumber
     * @param productNumber
     * @param employeeNumber
     * @param productAmount
     * @param date
     * @param orderNumber
     * @return
     */
    Integer updateBuyOrder(String supplierNumber,String productNumber,String employeeNumber,Long productAmount,String date,String orderNumber)throws Exception;
    /**
     * 清空数据
     * @return
     */
    Integer deleteAll() throws SQLException;
}
