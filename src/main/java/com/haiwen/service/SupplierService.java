package com.haiwen.service;

import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.Supplier;

public interface SupplierService {
    /**
     * 查找供应商信息
     * @param supplierName
     * @param supplierNumber
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageInfo selectSupplier(String supplierNumber, String supplierName, Integer pageSize, Integer pageNumber);
    /**
     * 删除一个供应商信息
     * @param supplierNumber
     * @param buyOrderSupplierID
     * @return
     * @throws RuntimeException
     */
    Integer deleteOneSupplier(String supplierNumber,Long buyOrderSupplierID) throws RuntimeException;
    /**
     * 清空
     * @return
     */
    Integer deleteAll()throws RuntimeException;
    /**
     * 插入一个供应商信息
     * @param supplier
     * @return 返回主键值
     */
    Long insertSupplier(Supplier supplier)throws RuntimeException;
    /**
     * 通过供应商ID查找一个供应商信息
     * @param id
     * @return
     */
    Supplier selectSupplier(Long id);
    /**
     * 修改供应商信息
     * @param supplier
     * @return
     */
    Boolean updateSupplier(Supplier supplier)throws RuntimeException;
}
