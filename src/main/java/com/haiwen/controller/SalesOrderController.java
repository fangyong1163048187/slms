package com.haiwen.controller;

import com.haiwen.pojo.PageInfo;
import com.haiwen.service.SalesOrderService;
import com.sun.tools.internal.xjc.ModelLoader;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SalesOrderController {
    @Resource
    private SalesOrderService salesOrderServiceImpl;
    //跳转到订货单信息页面
    @RequestMapping("order-info")
    public String orderInfo(){
        return "order-info";
    }
    //查找订货单信息
    @RequestMapping("order_select")
    public String selectSalesOrdernfo(String orderNumber,String employeeName, Model model, @RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize, @RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        PageInfo pageInfo = salesOrderServiceImpl.selectSalesOrder(orderNumber, employeeName, pageSize, pageNumber);
        //把每页的订货单信息存入model对象中，model对象在request域中可见
        model.addAttribute("page",pageInfo);
        return "order-info";
    }
    //删除一个订货单信息,就是取消订单
    @RequestMapping("order_delete_one")
    @ResponseBody
    public Map<String,Object> deleteOneSalesOrderInfo(String orderNumber, @RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize, @RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        Integer row = null;
        try {
            row = salesOrderServiceImpl.deleteOneSalesOrder(orderNumber);
        } catch (Exception e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(SalesOrderController.class);
            logger.info(e.getMessage());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("row",row);
        return map;
    }

    /**
     * 清空
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @RequestMapping("order_truncate")
    public String orderTruncate(@RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize,@RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        //返回受影响的行数
        Integer rows = null;
        try {
            rows = salesOrderServiceImpl.deleteAll();
        } catch (Exception e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(SalesOrderController.class);
            logger.info(e.getMessage());
        }
        if(rows!=null){
            return "redirect:order_select?orderNumber="+""+"&employeeName="+""+"&pageSize="+pageSize+"&pageNumber="+pageNumber;
        }else{
            return "error";
        }
    }

    /**
     * 跳转到添加订单表信息页面
     * @return
     */
    @RequestMapping("order-insert-page")
    public String insertProductPage(Model model){
        //查找所有客户
        //查找所有商品
        //查找所有经办人
        Map<String, Object> map = salesOrderServiceImpl.selectCusProEmp();
        model.addAttribute("customers",map.get("customers"));
        model.addAttribute("products",map.get("products"));
        model.addAttribute("employees",map.get("employees"));
        return "order-insert";
    }

    /**
     * 判断库存是否充足
     * @param number 商品名
     * @param amount 商品数量
     * @return
     */
    @RequestMapping("judgeStockAmount")
    @ResponseBody
    public Map<String,Object> judgeStockAmount(Long amount,String number){
        Boolean isSuccess = salesOrderServiceImpl.selectJudgeStockAmount(amount, number);
        Map<String,Object> map = new HashMap<>();
        map.put("isSuccess",isSuccess);
        return map;
    }
    //新增订货单信息
    @RequestMapping(value = "order-insert")
    @ResponseBody
    public Map<String, Object> insertProduct(@RequestParam("customer_name") String customerNumber, @RequestParam("product_name") String productNumber, @RequestParam("employee_name") String employeeNumber,@RequestParam("product_amount") Long productAmount){
        String salesOrderNumber = null;
        try {
            salesOrderNumber = salesOrderServiceImpl.insertSalesOrder(customerNumber, productNumber, employeeNumber, productAmount);
            //日志记录
            Logger logger = Logger.getLogger(SalesOrderController.class);
            logger.info("插入数据成功");
        } catch (Exception e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(SalesOrderController.class);
            logger.info("插入数据失败");
        }
        //转为json字符串后在发送
        Map<String,Object> map = new HashMap<>();
        map.put("salesOrderNumber",salesOrderNumber);
        return map;
    }
    //跳转到修改订单表信息页面
    @RequestMapping("order-update-page")
    public String updateOrderInfo(String orderNumber, Model model){
        //通过要修改的那个订单的订单编号查找订单信息
        PageInfo pageInfo = salesOrderServiceImpl.selectSalesOrder(orderNumber, "", 8, 1);
        model.addAttribute("page",pageInfo);
        //查找所有客户
        //查找所有商品
        //查找所有经办人
        Map<String, Object> map = salesOrderServiceImpl.selectCusProEmp();
        model.addAttribute("customers",map.get("customers"));
        model.addAttribute("products",map.get("products"));
        model.addAttribute("employees",map.get("employees"));
        return "order-update";
    }
    //处理订货单信息修改
    @RequestMapping("order-update")
    @ResponseBody
    public Map<String,Object> SalesOrderUpdate(@RequestParam("customer_name") String customerNumber, @RequestParam("product_name") String productNumber, @RequestParam("employee_name") String employeeNumber,@RequestParam("product_amount") Long productAmount,String date,String salesOrderNumber){
        Integer row = null;
        try {
            row = salesOrderServiceImpl.updateSalesOrder(customerNumber,productNumber,employeeNumber,productAmount,date,salesOrderNumber);
        } catch (Exception e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(SalesOrderController.class);
            logger.info(e.getMessage());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("row",row);
        return map;
    }
}
