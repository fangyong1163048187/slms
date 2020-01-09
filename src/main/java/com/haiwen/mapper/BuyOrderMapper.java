package com.haiwen.mapper;

import com.haiwen.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuyOrderMapper {
    /**
     * 通过订单编号和经办人姓名查询相关订单
     * @param orderNumber
     * @param employeeName
     * @param pageInfo
     * @return
     */
    List<Buyorder> selectByBuyOrderNumAndPsd(@Param("orderNumber")String orderNumber, @Param("employeeName")String employeeName, @Param("pageInfo") PageInfo pageInfo);
    /**
     * 查询符合查询条件的订单表总记录数
     * @param orderNumber
     * @param employeeName
     * @return
     */
    Integer selectCountOfBuyOrder(@Param("orderNumber")String orderNumber, @Param("employeeName")String employeeName);
    /**
     * 删除一个订单信息
     * @param orderNumber 订单编号
     * @return
     */
    Integer deleteOneBuyOrder(@Param("orderNumber")String orderNumber);
    /**
     * 修改库存表
     * @param buyorder
     * @return
     */
    Integer updateStock(Buyorder buyorder);
    /**
     * 通过订单编号查询相关订单
     * @param buyOrderNumber
     * @return
     */
    Buyorder selectBuyOrderByNumber(String buyOrderNumber);
    /**
     * 插入订单表
     * @param buyorder
     * @return
     */
    Integer insertBuyOrder(Buyorder buyorder);
    /**
     * 通过订单表ID查找订单表编号
     * @param buyOrderId
     * @return
     */
    Buyorder selectBuyOrderNumber(Long buyOrderId);
    /**
     * 查找所有客户
     * @return
     */
    List<Supplier> selectAllSupplier();

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
     * 通过供应商编号查找供应商ID和供应商名字
     * @param supplierNumber
     * @return
     */
    Supplier selectSupplier(String supplierNumber);

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
     * 修改进货表
     * @param buyorder
     * @return
     */
    Integer updateBuyOrder(Buyorder buyorder);
    /**
     * 清空数据
     * @return
     */
    Integer deleteAll();
}
