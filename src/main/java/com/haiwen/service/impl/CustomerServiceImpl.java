package com.haiwen.service.impl;

import com.haiwen.mapper.CustomerMapper;
import com.haiwen.pojo.Customer;
import com.haiwen.pojo.Employee;
import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.Salesorder;
import com.haiwen.service.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;
    @Override
    public PageInfo selectCustomer(String customerNumber, String customerName, Integer pageSize, Integer pageNumber) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCustomerNumber(customerNumber);
        pageInfo.setCustomerName(customerName);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageStart((pageNumber-1)*pageSize);
        //查不到结果时，list<object>类型返回一个实例化对象，大小为0，即调用size时为0
        List<Customer> customers = customerMapper.selectByCusNumAndPsd(customerNumber, customerName, pageInfo);
        pageInfo.setList(customers);
        //查找到的符合条件的客户总人数
        Integer countOfCustomer = customerMapper.selectCountOfCustomer(customerNumber,customerName);
        //计算总页数
        Integer pageTotal = ((countOfCustomer%pageSize)==0)?(countOfCustomer/pageSize):(countOfCustomer/pageSize+1);
        //当客户人数为0时，总页数为1
        if(countOfCustomer==0){
            pageTotal = 1;
        }
        //把客户总人数放入pageInfo对象
        pageInfo.setPageCountOfCustomer(countOfCustomer);
        //把总页数放入pageInfo对象
        pageInfo.setPageTotal(pageTotal);
        return pageInfo;
    }
    //通过客户编号删除一个客户信息,返回受影响的行数
    @Override
    //子类抛了异常，父类也要抛异常
    public Integer deleteOneCustomer (@Param("customerNumber")String customerNumber, @Param("salesOrderCustomerID")Long salesOrderCustomerID) throws RuntimeException{
        //判断父表的某条记录在子表里是否有关联数据，大于0，表示有
        Integer is = customerMapper.JudgeHavaDataInSonTable(salesOrderCustomerID);
        //把is作为标志变量传递
        Integer row = customerMapper.deleteOneCustomer(customerNumber,salesOrderCustomerID,is);
        //当执行到一半失败时，手动抛出异常，让服务器捕捉，从而进行回滚操作
        if(row==0){
            throw new java.lang.RuntimeException("删除数据失败");
        }
        return row;
    }
    //清空数据
    @Override
    /*@Pointcut("execution(* com.haiwen.service.impl.EmployeeServiseImpl.truncate())")*/
    public Integer deleteAll()throws RuntimeException{
        Integer row1 = customerMapper.deleteAllSalesOrder();
        Integer row2 = customerMapper.deleteAllCustomer();
        if(row2==0){
            throw new RuntimeException("清空失败");
        }
        return row2;
    }
    //插入员工信息
    @Override
    public Long insertCustomer(Customer customer) throws RuntimeException{
        Long customerId = null;
        Integer row = customerMapper.insertCustomer(customer);
        if(row==0){
            throw new RuntimeException("插入顾客信息失败");
        }
        //获取插入的记录的主键ID
        customerId = customer.getId();
        return customerId;
    }
    //通过顾客ID查找顾客编号
    @Override
    public Customer selectCustomer(Long id) {
        Customer customer = customerMapper.selectCustomer(id);
        return customer;
    }
    //修改客户数据
    @Override
    public Boolean updateCustomer(Customer customer) throws RuntimeException{
        //标志操作是否执行成功
        Boolean isSuccess = true;
        Integer row1 = customerMapper.updateCustomer(customer);
        Salesorder salesorder = new Salesorder();
        salesorder.setCustomername(customer.getCustomername());
        salesorder.setCustomerid(customer.getId());
        Integer is = customerMapper.JudgeHavaDataInSonTable(customer.getId());
        Integer row2 = customerMapper.updateSalesOrder(salesorder, is);
        if(row1==0){
            isSuccess=false;
            throw new RuntimeException("修改客户数据失败");
        }
        return isSuccess;
    }
}
