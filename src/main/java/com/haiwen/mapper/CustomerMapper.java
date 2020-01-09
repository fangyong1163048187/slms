package com.haiwen.mapper;

import com.haiwen.pojo.Customer;
import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.Salesorder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    /**
     * 通过客户编号和客户名称查找客户信息
     * @param customerNumber 为""时，不查找该条件
     * @param customerName 为""时，不查找该条件
     * @param pageInfo 分页查找
     * @return 返回客户信息
     */
    List<Customer> selectByCusNumAndPsd(@Param("customerNumber")String customerNumber, @Param("customerName")String customerName, @Param("pageInfo") PageInfo pageInfo);
    /**
     * 查找符合条件的客户总人数
     * @return
     */
    Integer selectCountOfCustomer(@Param("customerNumber")String customerNumber, @Param("customerName")String customerName);

    /**
     * 判断某个父表在子表里是否有关联数据
     * @param salesOrderCustomerID
     * @return 大于0表示有
     */
    Integer JudgeHavaDataInSonTable(@Param("salesOrderCustomerID")Long salesOrderCustomerID);
    /**
     * 删除一个客户信息
     * @param customerNumber
     * @return
     */
    Integer deleteOneCustomer(@Param("customerNumber")String customerNumber,@Param("salesOrderCustomerID")Long salesOrderCustomerID,@Param("is")Integer is);
    /**
     * 清空数据
     * @return
     */
    Integer deleteAllSalesOrder();
    Integer deleteAllCustomer();
    /**
     * 向父表插入一个顾客信息
     * @param customer
     * @return
     */
    Integer insertCustomer(Customer customer);
    /**
     * 向子表插入信息
     * @param salesorder
     * @return
     */
    Integer insertSalesorder(Salesorder salesorder);
    /**
     * 通过顾客主键查找顾客信息
     * @param id
     * @return
     */
    Customer selectCustomer(@Param("id")Long id);
    /**
     * 修改顾客信息
     * @param customer
     * @return
     */
    Integer updateCustomer(Customer customer);

    /**
     * 修改顾客信息时还要修改订单表里的顾客姓名
     * @param salesorder 订单信息
     * @param is 判断是否要修改订单表的条件
     * @return
     */
    Integer updateSalesOrder(@Param("salesorder") Salesorder salesorder,@Param("is") Integer is);
}
