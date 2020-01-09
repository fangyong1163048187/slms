package com.haiwen.controller;

import com.haiwen.pojo.PageInfo;
import com.haiwen.service.BuyOrderService;
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
public class BuyOrderController {
    @Resource
    private BuyOrderService buyOrderServiceImpl;
    //跳转到订货单信息页面
    @RequestMapping("buy-info")
    public String orderInfo(){
        return "buy-info";
    }
    //查找订货单信息
    @RequestMapping("buy_select")
    public String selectBuyOrderInfo(String orderNumber, String employeeName, Model model, @RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize, @RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        PageInfo pageInfo = buyOrderServiceImpl.selectBuyOrder(orderNumber, employeeName, pageSize, pageNumber);
        //把每页的订货单信息存入model对象中，model对象在request域中可见
        model.addAttribute("page",pageInfo);
        return "buy-info";
    }
    //删除一个订货单信息,就是取消订单
    @RequestMapping("buy_delete_one")
    @ResponseBody
    public Map<String,Object> deleteOneSalesOrderInfo(String orderNumber, @RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize, @RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        Integer row = null;
        try {
            row = buyOrderServiceImpl.deleteOneBuyOrder(orderNumber);
        } catch (Exception e) {
            Logger logger = Logger.getLogger(BuyOrderController.class);
            logger.info(e.getMessage());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("row",row);
        return map;
    }
    /**
     * 跳转到添加订单表信息页面
     * @return
     */
    @RequestMapping("buy-insert-page")
    public String insertProductPage(Model model){
        //查找所有供应商
        //查找所有商品
        //查找所有经办人
        Map<String, Object> map = buyOrderServiceImpl.selectSupProEmp();
        model.addAttribute("suppliers",map.get("suppliers"));
        model.addAttribute("products",map.get("products"));
        model.addAttribute("employees",map.get("employees"));
        return "buy-insert";
    }
    //新增订货单信息
    @RequestMapping(value = "buy-insert")
    @ResponseBody
    public Map<String, Object> insertProduct(@RequestParam(value = "supplier_name") String supplierNumber, @RequestParam("product_name") String productNumber, @RequestParam("employee_name") String employeeNumber,@RequestParam("product_amount") Long productAmount){
        String buyOrderNumber = null;
        try {
            buyOrderNumber = buyOrderServiceImpl.insertBuyOrder(supplierNumber, productNumber, employeeNumber, productAmount);
            //日志记录
            Logger logger = Logger.getLogger(BuyOrderController.class);
            logger.info("插入数据成功");
        } catch (Exception e) {
            Logger logger = Logger.getLogger(BuyOrderController.class);
            logger.info("插入数据失败");
        }
        //转为json字符串后在发送
        Map<String,Object> map = new HashMap<>();
        map.put("buyOrderNumber",buyOrderNumber);
        return map;
    }
    //跳转到修改订单表信息页面
    @RequestMapping("buy-update-page")
    public String updateOrderInfo(String orderNumber, Model model){
        //通过要修改的那个订单的订单编号查找订单信息
        PageInfo pageInfo = buyOrderServiceImpl.selectBuyOrder(orderNumber, "", 8, 1);
        model.addAttribute("page",pageInfo);
        //查找所有客户
        //查找所有商品
        //查找所有经办人
        Map<String, Object> map = buyOrderServiceImpl.selectSupProEmp();
        model.addAttribute("suppliers",map.get("suppliers"));
        model.addAttribute("products",map.get("products"));
        model.addAttribute("employees",map.get("employees"));
        return "buy-update";
    }
    //处理订货单信息修改
    @RequestMapping("buy-update")
    @ResponseBody
    public Map<String,Object> buyOrderUpdate(@RequestParam("supplier_name") String supplierNumber, @RequestParam("product_name") String productNumber, @RequestParam("employee_name") String employeeNumber,@RequestParam("product_amount") Long productAmount,String date,String buyOrderNumber){
        Integer row = null;
        try {
            row = buyOrderServiceImpl.updateBuyOrder(supplierNumber,productNumber,employeeNumber,productAmount,date,buyOrderNumber);
        } catch (Exception e) {
            Logger logger = Logger.getLogger(BuyOrderController.class);
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
    @RequestMapping("buy_truncate")
    public String orderTruncate(@RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize,@RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        //返回受影响的行数
        Integer rows = null;
        try {
            rows = buyOrderServiceImpl.deleteAll();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(BuyOrderController.class);
            logger.info(e.getMessage());
        }
        if(rows!=null){
            return "redirect:buy_select?orderNumber="+""+"&employeeName="+""+"&pageSize="+pageSize+"&pageNumber="+pageNumber;
        }else{
            return "error";
        }
    }
}
