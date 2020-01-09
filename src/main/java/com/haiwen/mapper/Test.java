package com.haiwen.mapper;

import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.Product;
import com.haiwen.pojo.Salesorder;
import com.haiwen.pojo.Stock;
import com.haiwen.service.impl.EmployeeServiseImpl;
import com.haiwen.service.impl.ProductServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext a = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomerMapper customerMapper = a.getBean("customerMapper", CustomerMapper.class);
        Salesorder salesorder = new Salesorder();
        salesorder.setCustomerid(3);
        salesorder.setCustomername("abc");
        Integer integer = customerMapper.updateSalesOrder(salesorder, 0);
        System.out.println(integer);
    }
}
