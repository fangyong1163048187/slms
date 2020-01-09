package com.haiwen.service.impl;

import com.haiwen.mapper.SupplierMapper;
import com.haiwen.pojo.Buyorder;
import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.Supplier;
import com.haiwen.service.SupplierService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Resource
    private SupplierMapper supplierMapper;
    @Override
    public PageInfo selectSupplier(String supplierNumber, String supplierName, Integer pageSize, Integer pageNumber) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setSupplierNumber(supplierNumber);
        pageInfo.setSupplierName(supplierName);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageStart((pageNumber-1)*pageSize);
        //查不到结果时，list<object>类型返回一个实例化对象，大小为0，即调用size时为0
        List<Supplier> suppliers = supplierMapper.selectBySupNumAndPsd(supplierNumber, supplierName, pageInfo);
        pageInfo.setList(suppliers);
        //查找到的符合条件的供应商总人数
        Integer countOfSupplier = supplierMapper.selectCountOfSupplier(supplierNumber,supplierName);
        //计算总页数
        Integer pageTotal = ((countOfSupplier%pageSize)==0)?(countOfSupplier/pageSize):(countOfSupplier/pageSize+1);
        //当供应商人数为0时，总页数为1
        if(countOfSupplier==0){
            pageTotal = 1;
        }
        //把供应商总人数放入pageInfo对象
        pageInfo.setPageCountOfSupplier(countOfSupplier);
        //把总页数放入pageInfo对象
        pageInfo.setPageTotal(pageTotal);
        return pageInfo;
    }
    //通过供应商编号删除一个供应商信息,返回受影响的行数
    @Override
    public Integer deleteOneSupplier(String supplierNumber, Long buyOrderSupplierID) throws RuntimeException {
        //判断父表的某条记录在子表里是否有关联数据，大于0，表示有
        Integer is = supplierMapper.JudgeHavaDataInSonTable(buyOrderSupplierID);
        //把is作为标志变量传递
        Integer row = supplierMapper.deleteOneSupplier(supplierNumber,buyOrderSupplierID,is);
        //当执行到一半失败时，手动抛出异常，让服务器捕捉，从而进行回滚操作
        if(row==0){
            throw new java.lang.RuntimeException("删除数据失败");
        }
        return row;
    }
    //清空数据
    @Override
    public Integer deleteAll() throws RuntimeException {
        Integer row1 = supplierMapper.deleteAllBuyOrder();
        Integer row2 = supplierMapper.deleteAllSupplier();
        if(row2==0){
            throw new RuntimeException("清空失败");
        }
        return row2;
    }
    //插入供应商信息
    @Override
    public Long insertSupplier(Supplier supplier) throws RuntimeException {
        Long supplierId = null;
        Integer row = supplierMapper.insertSupplier(supplier);
        if(row==0){
            throw new RuntimeException("插入供应商信息失败");
        }
        //获取插入的记录的主键ID
        supplierId = supplier.getId();
        return supplierId;
    }
    //通过供应商ID查找供应商编号
    @Override
    public Supplier selectSupplier(Long id) {
        Supplier supplier = supplierMapper.selectSupplier(id);
        return supplier;
    }
    //修改供应商数据
    @Override
    public Boolean updateSupplier(Supplier supplier) throws RuntimeException {
        //标志操作是否执行成功
        Boolean isSuccess = true;
        Integer row1 = supplierMapper.updateSupplier(supplier);
        Buyorder buyorder = new Buyorder();
        buyorder.setSuppliername(supplier.getSuppliername());
        buyorder.setSupplierid(supplier.getId());
        Integer is = supplierMapper.JudgeHavaDataInSonTable(supplier.getId());
        Integer row2 = supplierMapper.updateBuyOrder(buyorder, is);
        if(row1==0){
            isSuccess=false;
            throw new RuntimeException("修改供应商数据失败");
        }
        return isSuccess;
    }
}
