package com.haiwen.controller;

import com.haiwen.pojo.Customer;
import com.haiwen.pojo.PageInfo;
import com.haiwen.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomerController {
    @Resource
    private CustomerService customerServiceImpl;
    //跳转到客户信息页面
    @RequestMapping("customer-info")
    public String customerInfo(){
        return "customer-info";
    }
    @RequestMapping("customer_select")
    public String selectCustomerInfo(@RequestParam("customer_id")String customerId, @RequestParam("customer_name")String customerName, Model model, @RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize, @RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber/*,String status*/){
        PageInfo pageInfo = customerServiceImpl.selectCustomer(customerId, customerName, pageSize, pageNumber);
        //把每页的客户信息存入model对象中，model对象在request域中可见
        model.addAttribute("customer",pageInfo);
        /*model.addAttribute("status",status);*/
        return "customer-info";
    }
    //删除一个客户信息
    @RequestMapping("customer_delete_one")
    @ResponseBody
    //customerNumber为要删除的客户编号，customerId为要查找的客户编号
    public Map<String,Object> deleteOneCustomerInfo(String customerNumber,Long salesOrderCustomerID,@RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize, @RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        Integer row = null;
        try {
            row = customerServiceImpl.deleteOneCustomer(customerNumber,salesOrderCustomerID);
        } catch (RuntimeException e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(CustomerController.class);
            logger.info(e.getMessage());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("row",row);
        return map;
    }
    //清空所有员工信息
    @RequestMapping("customer_truncate")
    public String customerTruncate(@RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize,@RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        //返回受影响的行数
        Integer rows = null;
        try {
            rows = customerServiceImpl.deleteAll();
        } catch (RuntimeException e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(CustomerController.class);
            logger.info(e.getMessage());
        }
        if(rows!=null){
            return "redirect:customer_select?customer_id="+""+"&customer_name="+""+"&pageSize="+pageSize+"&pageNumber="+pageNumber;
        }else{
            return "error";
        }
    }
    //跳转到添加员工信息页面
    @RequestMapping("customer-insert-page")
    public String insertCustomerPage(){
        return "customer-insert";
    }
    //新增员工信息
    @RequestMapping(value = "customer-insert")
    @ResponseBody
    public Map<String, Object> insertCustomer(String name, String linkman, String linkphone, String linkaddress){
        //插入是否成功的标志，默认为null，表示不成功
        String customerNumber = null;
        try {
            //把用户信息封装在类Customer中
            Customer customer = new Customer();
            customer.setCustomername(name);
            customer.setLinkman(linkman);
            customer.setLinkphone(linkphone);
            customer.setLinkaddress(linkaddress);
            //调用service层方法,返回主键ID
            Long customerId = customerServiceImpl.insertCustomer(customer);
            //利用customerId查找customerNumber
            Customer customer1 = customerServiceImpl.selectCustomer(customerId);
            customerNumber = customer1.getCustomernumber();
            //日志记录
            Logger logger = Logger.getLogger(CustomerController.class);
            logger.info("插入数据成功，插入顾客姓名:"+name);
        } catch (Exception e1) {
            Logger logger = Logger.getLogger(CustomerController.class);
            logger.info("插入数据失败，失败原因:"+e1.getMessage());
        }
        //转为json字符串后在发送
        Map<String,Object> map = new HashMap<>();
        map.put("customerNumber",customerNumber);
        return map;
    }
    //跳往修改员工信息页面
    @RequestMapping("/customer-update")
    public String updateCustomerInfo(Model model,Long customerId,String customerNumber,String customerName,String linkman,String linkphone,String linkaddress){
        Customer customer = new Customer();
        customer.setCustomernumber(customerNumber);
        customer.setCustomername(customerName);
        customer.setLinkman(linkman);
        customer.setLinkphone(linkphone);
        customer.setLinkaddress(linkaddress);
        customer.setId(customerId);
        model.addAttribute("customer",customer);
        return "customer-update";
    }
    //处理商品信息修改
    @RequestMapping("customer-update-after")
    @ResponseBody
    public Map<String,Object> customerUpdate(String number,String name,String linkman,String linkphone,String linkaddress,Long customerid){
        Boolean isSuccess = false;
        Customer customer = new Customer();
        customer.setCustomernumber(number);
        customer.setCustomername(name);
        customer.setLinkman(linkman);
        customer.setLinkphone(linkphone);
        customer.setLinkaddress(linkaddress);
        customer.setId(customerid);
        try {
            isSuccess = customerServiceImpl.updateCustomer(customer);
        } catch (Exception e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(CustomerController.class);
            logger.info("修改数据失败:"+e.getMessage());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("isSuccess",isSuccess);
        return map;
    }
}
