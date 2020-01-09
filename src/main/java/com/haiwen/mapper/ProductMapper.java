package com.haiwen.mapper;

import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.Product;
import com.haiwen.pojo.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    /**
     * 通过商品编号和商品名称查找商品信息
     * @param productNumber 为""时，不查找该条件
     * @param productName 为""时，不查找该条件
     * @param pageInfo 分页查找
     * @return 返回商品信息
     */
    List<Product> selectByProNumAndPsd(@Param("productNumber")String productNumber, @Param("productName")String productName, @Param("pageInfo") PageInfo pageInfo);
    /**
     * 查找符合条件的商品总数
     * @return
     */
    Integer selectCountOfProduct(@Param("productNumber")String productNumber, @Param("productName")String productName);
    /**
     * 删除一个商品信息
     * @param productNumber
     * @return
     */
    Integer deleteOneProduct(@Param("productNumber")String productNumber,@Param("stockProductID")Long stockProductID);
    /**
     * 清空数据
     * @return
     */
    Integer deleteAll();
    /**
     * 向父表插入一个商品信息
     * @param product
     * @return
     */
    Integer insertProduct(Product product);
    /**
     * 向子表插入信息
     * @param stock
     * @return
     */
    Integer insertStockProductId(Stock stock);
    /**
     * 通过商品主键查找商品信息
     * @param id
     * @return
     */
    Product selectProduct(@Param("id")Long id);

    /**
     * 修改商品信息
     * @param product
     * @param is
     * @return
     */
    Integer updateProduct(@Param("product") Product product,@Param("is") Integer is);
    /**
     * 判断父表的某个记录在子表里是否有关联数据
     * @param id
     * @return 大于0表示有
     */
    Integer judgeHavaDataInSonTable(@Param("id")Long id);
}
