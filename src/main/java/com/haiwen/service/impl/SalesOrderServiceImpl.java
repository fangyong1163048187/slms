package com.haiwen.service.impl;

import com.haiwen.mapper.SalesOrderMapper;
import com.haiwen.pojo.*;
import com.haiwen.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
/*查询订货信息*/
public class SalesOrderServiceImpl implements SalesOrderService {
    @Autowired
    private SalesOrderMapper salesOrderMapper;
    @Override
    public PageInfo selectSalesOrder(String orderNumber, String employeeName, Integer pageSize, Integer pageNumber) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setSalesOrderNumber(orderNumber);
        pageInfo.setEmpName(employeeName);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageStart((pageNumber-1)*pageSize);
        List<Salesorder> salesorders = salesOrderMapper.selectBySalesOrderNumAndPsd(orderNumber, employeeName, pageInfo);
        pageInfo.setList(salesorders);
        //查找到的符合条件的订单表总数
        Integer countOfSalesOrder = salesOrderMapper.selectCountOfSalesOrder(orderNumber,employeeName);
        //计算总页数
        Integer pageTotal = ((countOfSalesOrder%pageSize)==0)?(countOfSalesOrder/pageSize):(countOfSalesOrder/pageSize+1);
        //当订单表数为0时，总页数为1
        if(countOfSalesOrder==0){
            pageTotal = 1;
        }
        //把订单表总数放入pageInfo对象
        pageInfo.setPageCountOfSalesOrder(countOfSalesOrder);
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
    public Integer deleteOneSalesOrder(String orderNumber) throws SQLException{
        Integer isSuccss = null;
        //通过订单编号获取订单信息
        Salesorder salesorder = salesOrderMapper.selectSalesOrderByNumber(orderNumber);
        //获取订单表里的商品数量,并置为相反数
        long productamount = -salesorder.getProductamount();
        Integer row = salesOrderMapper.deleteOneSalesOrder(orderNumber);
        //取消订单后，更改库存信息
        salesorder.setProductamount(productamount);
        Integer row2 = salesOrderMapper.updateStock(salesorder);
        if(row==0 || row2==0){
            throw new SQLException("删除失败");
        }else{
            isSuccss = 1;
        }
        return isSuccss;
    }

    /**
     * 清空
     * @return
     * @throws SQLException
     */
    @Override
    public Integer deleteAll() throws SQLException {
        Integer row = salesOrderMapper.deleteAll();
        if(row==0){
            throw new SQLException("清空数据失败");
        }
        return row;
    }
    /**
     * 查找所有顾客、商品、经办人
     * @return
     */
    @Override
    public Map<String, Object> selectCusProEmp() {
        List<Customer> customers = salesOrderMapper.selectAllCustomer();
        List<Product> products = salesOrderMapper.selectAllProduct();
        List<Employee> employees = salesOrderMapper.selectAllEmployee();
        //存入map集合中
        Map<String, Object> map =new HashMap<>();
        map.put("customers",customers);
        map.put("products",products);
        map.put("employees",employees);
        return map;
    }

    /**
     * 判断库存是否充足
     * @param amount
     * @param number
     * @return
     */
    @Override
    public Boolean selectJudgeStockAmount(Long amount, String number) {
        //标志变量
        Boolean isSuccess = false;
        //stockAmount为查找出来的商品库存数量
        Long stockAmount = salesOrderMapper.selectStockAmount(number);
        if(stockAmount>=amount){
            //库存充足
            isSuccess = true;
        }
        return isSuccess;
    }

    /**
     * 插入订单表
     * @param customerNumber 顾客编号
     * @param productNumber 商品编号
     * @param employeeNumber 员工编号
     * @param productAmount 商品数量
     * @return
     */
    @Override
    public String insertSalesOrder(String customerNumber, String productNumber, String employeeNumber, Long productAmount)throws SQLException{
        String salesOrderNumber = null;
        //通过顾客编号查找顾客ID和顾客名字
        Customer customer = salesOrderMapper.selectCustomer(customerNumber);
        Long customerId = customer.getId();
        String customerName = customer.getCustomername();
        //通过商品编号查找商品ID和商品单价
        Product product = salesOrderMapper.selectProduct(productNumber);
        Long productId = product.getId();
        Double  productSalesPrice = product.getProductsalesprice();
        //通过员工编号查找员工ID
        Long employeeId = salesOrderMapper.selectEmployeeId(employeeNumber);
        Salesorder salesorder = new Salesorder();
        salesorder.setCustomerid(customerId);
        salesorder.setProductid(productId);
        salesorder.setEmployeeid(employeeId);
        salesorder.setProductamount(productAmount);
        salesorder.setCustomername(customerName);
        //计算总价钱
        Double totalMoney = productSalesPrice*productAmount;
        salesorder.setProductallprice(totalMoney);
        Integer row = salesOrderMapper.insertSalesOrder(salesorder);
        Integer row2 = salesOrderMapper.updateStock(salesorder);
        if(row==0 || row2==0){
            throw new SQLException("插入数据失败");
        }
        Long salesorderId = salesorder.getId();
        salesOrderNumber = salesOrderMapper.selectSalesOrderNumber(salesorderId).getOrdernumber();
        return salesOrderNumber;
    }

    /**
     * 修改
     * @param customerNumber
     * @param productNumber
     * @param employeeNumber
     * @param productAmount
     * @param date
     * @param orderNumber
     * @return
     */
    @Override
    public Integer updateSalesOrder(String customerNumber, String productNumber, String employeeNumber, Long productAmount, String date, String orderNumber)throws Exception {
        Integer isSuccess = null;
        Salesorder salesorder = new Salesorder();
        //通过顾客编号查找顾客ID和顾客名字
        Customer customer = salesOrderMapper.selectCustomer(customerNumber);
        Long customerId = customer.getId();
        String customerName = customer.getCustomername();
        //通过商品编号查找商品ID和商品单价
        Product product = salesOrderMapper.selectProduct(productNumber);
        Long productId = product.getId();
        Double  productSalesPrice = product.getProductsalesprice();
        //通过员工编号查找员工ID
        Long employeeId = salesOrderMapper.selectEmployeeId(employeeNumber);
        salesorder.setProductid(productId);
        salesorder.setCustomerid(customerId);
        salesorder.setEmployeeid(employeeId);
        salesorder.setCustomername(customerName);
        //处理时间格式
        String newDate = date.replace("T", " ").concat(":00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseDate = simpleDateFormat.parse(newDate);
        Timestamp timestamp = new Timestamp(parseDate.getTime());
        salesorder.setCreatetime(timestamp);
        salesorder.setProductallprice(productSalesPrice*productAmount);
        salesorder.setOrdernumber(orderNumber);
        salesorder.setProductamount(productAmount);
        //查询要修改的订单的原先的订单数量
        long oldProductAmount = salesOrderMapper.selectSalesOrderByNumber(orderNumber).getProductamount();
        //执行修改操作
         Integer row = salesOrderMapper.updateSalesOrder(salesorder);
        //计算在库存表里真正要修改的订单数量
        salesorder.setProductamount(productAmount-oldProductAmount);
        //并修改相应的库存表
        Integer row2 = salesOrderMapper.updateStock(salesorder);
        if(row==0 || row2==0){
            throw new SQLException("修改订货单失败");
        }else{
            isSuccess = 1;
        }
        return isSuccess;
    }
}
