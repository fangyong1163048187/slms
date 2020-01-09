package com.haiwen.service.impl;

import com.haiwen.mapper.BuyOrderMapper;
import com.haiwen.pojo.*;
import com.haiwen.service.BuyOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuyOrderServiceImpl implements BuyOrderService {
    @Resource
    private BuyOrderMapper buyOrderMapper;
    @Override
    public PageInfo selectBuyOrder(String orderNumber, String employeeName, Integer pageSize, Integer pageNumber) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setSalesOrderNumber(orderNumber);
        pageInfo.setEmpName(employeeName);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageStart((pageNumber-1)*pageSize);
        List<Buyorder> buyorders = buyOrderMapper.selectByBuyOrderNumAndPsd(orderNumber, employeeName, pageInfo);
        pageInfo.setList(buyorders);
        //查找到的符合条件的订单表总数
        Integer countOfbuyOrder = buyOrderMapper.selectCountOfBuyOrder(orderNumber,employeeName);
        //计算总页数
        Integer pageTotal = ((countOfbuyOrder%pageSize)==0)?(countOfbuyOrder/pageSize):(countOfbuyOrder/pageSize+1);
        //当订单表数为0时，总页数为1
        if(countOfbuyOrder==0){
            pageTotal = 1;
        }
        //把订单表总数放入pageInfo对象
        pageInfo.setPageCountOfSalesOrder(countOfbuyOrder);
        //把总页数放入pageInfo对象
        pageInfo.setPageTotal(pageTotal);
        return pageInfo;
    }
    /**
     * 删除一个订单信息
     * @param orderNumber 订单编号
     * @return
     */
    @Override
    public Integer deleteOneBuyOrder(String orderNumber) throws SQLException {
        Integer isSuccss = null;
        //通过订单编号获取订单信息
        Buyorder buyorder = buyOrderMapper.selectBuyOrderByNumber(orderNumber);
        //获取订单表里的商品数量,并置为相反数
        long productamount = -buyorder.getProductamount();
        Integer row = buyOrderMapper.deleteOneBuyOrder(orderNumber);
        //取消订单后，更改库存信息
        buyorder.setProductamount(productamount);
        Integer row2 = buyOrderMapper.updateStock(buyorder);
        if(row==0 || row2==0){
            throw new SQLException("删除失败");
        }else{
            isSuccss = 1;
        }
        return isSuccss;
    }

    /**
     * 查找所有顾客、商品、经办人
     * @return
     */
    @Override
    public Map<String, Object> selectSupProEmp() {
        List<Supplier> suppliers = buyOrderMapper.selectAllSupplier();
        List<Product> products = buyOrderMapper.selectAllProduct();
        List<Employee> employees = buyOrderMapper.selectAllEmployee();
        //存入map集合中
        Map<String, Object> map =new HashMap<>();
        map.put("suppliers",suppliers);
        map.put("products",products);
        map.put("employees",employees);
        return map;
    }

    /**
     * 插入订单表
     * @param supplierNumber 顾客编号
     * @param productNumber 商品编号
     * @param employeeNumber 员工编号
     * @param productAmount 商品数量
     * @return
     */
    @Override
    public String insertBuyOrder(String supplierNumber, String productNumber, String employeeNumber, Long productAmount)throws SQLException{
        String buyOrderNumber = null;
        //通过供应商编号查找供应商ID和供应商名字
        Supplier supplier = buyOrderMapper.selectSupplier(supplierNumber);
        Long supplierId = supplier.getId();
        String suppliername = supplier.getSuppliername();
        //通过商品编号查找商品ID和商品单价
        Product product = buyOrderMapper.selectProduct(productNumber);
        Long productId = product.getId();
        Double  productbuyprice = product.getProductbuyprice();
        //通过员工编号查找员工ID
        Long employeeId = buyOrderMapper.selectEmployeeId(employeeNumber);
        Buyorder buyorder = new Buyorder();
        buyorder.setSupplierid(supplierId);
        buyorder.setProductid(productId);
        buyorder.setEmployeeid(employeeId);
        buyorder.setProductamount(productAmount);
        buyorder.setSuppliername(suppliername);
        //计算总价钱
        Double totalMoney = productbuyprice*productAmount;
        buyorder.setProductallprice(totalMoney);
        Integer row = buyOrderMapper.insertBuyOrder(buyorder);
        Integer row2 = buyOrderMapper.updateStock(buyorder);
        if(row==0 || row2==0){
            throw new SQLException("插入数据失败");
        }
        Long buyorderId = buyorder.getId();
        buyOrderNumber = buyOrderMapper.selectBuyOrderNumber(buyorderId).getOrdernumber();
        return buyOrderNumber;
    }
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
    @Override
    public Integer updateBuyOrder(String supplierNumber, String productNumber, String employeeNumber, Long productAmount, String date, String orderNumber)throws Exception {
        Integer isSuccess = null;
        Buyorder buyorder = new Buyorder();
        //通过供应商编号查找供应商ID和供应商名字
        Supplier supplier = buyOrderMapper.selectSupplier(supplierNumber);
        Long customerId = supplier.getId();
        String supplierName = supplier.getSuppliername();
        //通过商品编号查找商品ID和商品单价
        Product product = buyOrderMapper.selectProduct(productNumber);
        Long productId = product.getId();
        Double  productbuyprice = product.getProductbuyprice();
        //通过员工编号查找员工ID
        Long employeeId = buyOrderMapper.selectEmployeeId(employeeNumber);
        buyorder.setProductid(productId);
        buyorder.setSupplierid(customerId);
        buyorder.setEmployeeid(employeeId);
        buyorder.setSuppliername(supplierName);
        //处理时间格式
        String newDate = date.replace("T", " ").concat(":00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseDate = simpleDateFormat.parse(newDate);
        Timestamp timestamp = new Timestamp(parseDate.getTime());
        buyorder.setCreatetime(timestamp);
        buyorder.setProductallprice(productbuyprice*productAmount);
        buyorder.setOrdernumber(orderNumber);
        buyorder.setProductamount(productAmount);
        //查询要修改的订单的原先的订单数量
        long oldProductAmount = buyOrderMapper.selectBuyOrderByNumber(orderNumber).getProductamount();
        //执行修改操作
        Integer row = buyOrderMapper.updateBuyOrder(buyorder);
        //计算在库存表里真正要修改的订单数量
        buyorder.setProductamount(productAmount-oldProductAmount);
        //并修改相应的库存表
        Integer row2 = buyOrderMapper.updateStock(buyorder);
        if(row==0 || row2==0){
            throw new SQLException("修改订货单失败");
        }else{
            isSuccess = 1;
        }
        return isSuccess;
    }
    /**
     * 清空
     * @return
     * @throws SQLException
     */
    @Override
    public Integer deleteAll() throws SQLException {
        Integer row = buyOrderMapper.deleteAll();
        if(row==0){
            throw new SQLException("清空数据失败");
        }
        return row;
    }
}
