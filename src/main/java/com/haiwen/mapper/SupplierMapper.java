package com.haiwen.mapper;

import com.haiwen.pojo.Buyorder;
import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierMapper {
    /**
     * 通过供应商编号和供应商名称查找供应商信息
     * @param supplierName 为""时，不查找该条件
     * @param supplierNumber 为""时，不查找该条件
     * @param pageInfo 分页查找
     * @return 返回供应商信息
     */
    List<Supplier> selectBySupNumAndPsd(@Param("supplierNumber")String supplierNumber, @Param("supplierName")String supplierName, @Param("pageInfo") PageInfo pageInfo);
    /**
     * 查找符合条件的供应商总数
     * @return
     */
    Integer selectCountOfSupplier(@Param("supplierNumber")String supplierNumber, @Param("supplierName")String supplierName);

    /**
     * 判断某个父表在子表里是否有关联数据
     * @param buyOrderSupplierID
     * @return 大于0表示有
     */
    Integer JudgeHavaDataInSonTable(@Param("buyOrderSupplierID")Long buyOrderSupplierID);
    /**
     * 删除一个供应商信息
     * @param buyOrderSupplierID
     * @return
     */
    Integer deleteOneSupplier(@Param("supplierNumber")String supplierNumber,@Param("buyOrderSupplierID")Long buyOrderSupplierID,@Param("is")Integer is);
    /**
     * 清空数据
     * @return
     */
    Integer deleteAllBuyOrder();
    Integer deleteAllSupplier();
    /**
     * 向父表插入一个供应商信息
     * @param supplier
     * @return
     */
    Integer insertSupplier(Supplier supplier);
    /**
     * 向子表插入信息
     * @param buyorder
     * @return
     */
    Integer insertBuyorder(Buyorder buyorder);
    /**
     * 通过供应商主键查找供应商信息
     * @param id
     * @return
     */
    Supplier selectSupplier(@Param("id")Long id);
    /**
     * 修改供应商信息
     * @param supplier
     * @return
     */
    Integer updateSupplier(Supplier supplier);

    /**
     * 修改供应商信息时还要修改采购表里的供应商姓名
     * @param buyorder 采购表信息
     * @param is 判断是否要修改采购表的条件
     * @return
     */
    Integer updateBuyOrder(@Param("buyorder") Buyorder buyorder,@Param("is") Integer is);
}
