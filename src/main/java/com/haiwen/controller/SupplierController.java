package com.haiwen.controller;

import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.Supplier;
import com.haiwen.service.SupplierService;
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
public class SupplierController {
    @Resource
    private SupplierService supplierServiceImpl;
    //跳转到供应商档案信息页面
    @RequestMapping("supplier-info")
    public String supplierInfo(){
        return "supplier-info";
    }
    //查询
    @RequestMapping("supplier_select")
    public String selectSupplierInfo(@RequestParam("supplier_id")String supplierId, @RequestParam("supplier_name")String supplierName, Model model, @RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize, @RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        PageInfo pageInfo = supplierServiceImpl.selectSupplier(supplierId, supplierName, pageSize, pageNumber);
        //把每页的供应商信息存入model对象中，model对象在request域中可见
        model.addAttribute("supplier",pageInfo);
        return "supplier-info";
    }
    //删除一个供应商信息
    @RequestMapping("supplier_delete_one")
    @ResponseBody
    //supplierNumber为要删除的供应商编号，supplierId为要查找的供应商编号
    public Map<String,Object> deleteOneSupplierInfo(String supplierNumber, Long buyOrderSupplierID, @RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize, @RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        Integer row = null;
        try {
            row = supplierServiceImpl.deleteOneSupplier(supplierNumber,buyOrderSupplierID);
        } catch (RuntimeException e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(SupplierController.class);
            logger.info(e.getMessage());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("row",row);
        return map;
    }
    //清空所有供应商信息
    @RequestMapping("supplier_truncate")
    public String supplierTruncate(@RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize,@RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        //返回受影响的行数
        Integer rows = null;
        try {
            rows = supplierServiceImpl.deleteAll();
        } catch (RuntimeException e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(SupplierController.class);
            logger.info(e.getMessage());
        }
        if(rows!=null){
            return "redirect:supplier_select?supplier_id="+""+"&supplier_name="+""+"&pageSize="+pageSize+"&pageNumber="+pageNumber;
        }else{
            return "error";
        }
    }
    //跳转到添加供应商信息页面
    @RequestMapping("supplier-insert-page")
    public String insertSupplierPage(){
        return "supplier-insert";
    }
    //新增供应商信息
    @RequestMapping(value = "supplier-insert")
    @ResponseBody
    public Map<String, Object> insertSupplier(String name, String linkman, String linkphone, String linkaddress){
        //插入是否成功的标志，默认为null，表示不成功
        String supplierNumber = null;
        try {
            //把用户信息封装在类Supplier中
            Supplier supplier = new Supplier();
            supplier.setSuppliername(name);
            supplier.setLinkman(linkman);
            supplier.setLinkphone(linkphone);
            supplier.setLinkaddress(linkaddress);
            //调用service层方法,返回主键ID
            Long supplierId = supplierServiceImpl.insertSupplier(supplier);
            //利用supplierId查找supplierNumber
            Supplier supplier1 = supplierServiceImpl.selectSupplier(supplierId);
            supplierNumber = supplier1.getSuppliernumber();
            //日志记录
            Logger logger = Logger.getLogger(SupplierController.class);
            logger.info("插入数据成功，插入供应商姓名:"+name);
        } catch (Exception e1) {
            Logger logger = Logger.getLogger(SupplierController.class);
            logger.info("插入数据失败，失败原因:"+e1.getMessage());
        }
        //转为json字符串后在发送
        Map<String,Object> map = new HashMap<>();
        map.put("supplierNumber",supplierNumber);
        return map;
    }
    //跳往修改供应商信息页面
    @RequestMapping("/supplier-update")
    public String updateSupplierInfo(Model model,Long supplierId,String supplierNumber,String supplierName,String linkman,String linkphone,String linkaddress){
        Supplier supplier = new Supplier();
        supplier.setSuppliernumber(supplierNumber);
        supplier.setSuppliername(supplierName);
        supplier.setLinkman(linkman);
        supplier.setLinkphone(linkphone);
        supplier.setLinkaddress(linkaddress);
        supplier.setId(supplierId);
        model.addAttribute("supplier",supplier);
        return "supplier-update";
    }
    //处理供应商信息修改
    @RequestMapping("supplier-update-after")
    @ResponseBody
    public Map<String,Object> supplierUpdate(String number,String name,String linkman,String linkphone,String linkaddress,Long supplierid){
        Boolean isSuccess = false;
        Supplier supplier = new Supplier();
        supplier.setSuppliernumber(number);
        supplier.setSuppliername(name);
        supplier.setLinkman(linkman);
        supplier.setLinkphone(linkphone);
        supplier.setLinkaddress(linkaddress);
        supplier.setId(supplierid);
        try {
            isSuccess = supplierServiceImpl.updateSupplier(supplier);
        } catch (Exception e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(SupplierController.class);
            logger.info("修改数据失败:"+e.getMessage());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("isSuccess",isSuccess);
        return map;
    }
}
