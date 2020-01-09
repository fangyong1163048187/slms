package com.haiwen.service;

import com.haiwen.pojo.Customer;
import com.haiwen.pojo.PageInfo;

public interface CustomerService {
    /**
     * 查找客户信息
     * @param customerNumber
     * @param customerName
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageInfo selectCustomer(String customerNumber, String customerName, Integer pageSize, Integer pageNumber);
    /**
     * 删除一个商品信息
     * @param customerNumber
     * @param salesOrderCustomerID
     * @return
     * @throws RuntimeException
     */
    Integer deleteOneCustomer(String customerNumber,Long salesOrderCustomerID) throws RuntimeException;
    /**
     * 清空
     * @return
     */
    Integer deleteAll()throws RuntimeException;
    /**
     * 插入一个顾客信息
     * @param customer
     * @return 返回主键值
     */
    Long insertCustomer(Customer customer)throws RuntimeException;
    /**
     * 通过顾客ID查找一个顾客信息
     * @param id
     * @return
     */
    Customer selectCustomer(Long id);
    /**
     * 修改客户信息
     * @param customer
     * @return
     */
    Boolean updateCustomer(Customer customer)throws RuntimeException;

}
