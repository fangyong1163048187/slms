package com.haiwen.service;

import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.Product;

public interface ProductService {
    /**
     * 查找商品信息
     * @param productNumber
     * @param productName
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageInfo selectProduct(String productNumber, String productName, Integer pageSize, Integer pageNumber);

    /**
     * 删除一个商品信息
     * @param productNumber
     * @param stockProductID
     * @return
     * @throws RuntimeException
     */
    Integer deleteOneProduct(String productNumber,Long stockProductID) throws RuntimeException;

    /**
     * 清空
     * @return
     * @throws RuntimeException
     */
    Integer deleteAll()throws RuntimeException;
    /**
     * 插入一个员工信息
     * @param product
     * @return 返回主键值
     */
    Long insertProduct(Product product)throws RuntimeException;
    /**
     * 通过商品ID查找一个商品信息
     * @param id
     * @return
     */
    Product selectProduct(Long id);
    /**
     * 修改商品信息
     * @param product
     * @return
     */
    Integer updateProduct(Product product)throws RuntimeException;
}
