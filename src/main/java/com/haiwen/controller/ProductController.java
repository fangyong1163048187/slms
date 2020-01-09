package com.haiwen.controller;

import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.Product;
import com.haiwen.pojo.Stock;
import com.haiwen.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController {
    @Resource
    private ProductService productServiceImpl;
    //跳转到商品信息页面
    @RequestMapping("product-info")
    public String productInfo(){
        return "product-info";
    }
    //查找员工信息
    @RequestMapping("product_select")
    public String selectProductInfo(@RequestParam("product_id")String productId, @RequestParam("product_name")String productName, Model model, @RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize, @RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber/*,String status*/){
        PageInfo pageInfo = productServiceImpl.selectProduct(productId, productName, pageSize, pageNumber);
        //把每页的商品信息存入model对象中，model对象在request域中可见
        model.addAttribute("product",pageInfo);
        /*model.addAttribute("status",status);*/
        return "product-info";
    }
    //删除一个员工信息
    @RequestMapping("product_delete_one")
    @ResponseBody
    //productNumber为要删除的商品编号，productId为要查找的商品编号
    public Map<String,Object> deleteOneProductInfo(String productNumber, @RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize,@RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber,Long stockProductID){
        Integer row = null;
        try {
            row = productServiceImpl.deleteOneProduct(productNumber,stockProductID);
        } catch (RuntimeException e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(ProductController.class);
            logger.info(e.getMessage());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("row",row);
        return map;
    }
    //清空所有员工信息
    @RequestMapping("product_truncate")
    public String productTruncate(@RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize,@RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        //返回受影响的行数
        Integer rows = null;
        try {
            rows = productServiceImpl.deleteAll();
        } catch (RuntimeException e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(ProductController.class);
            logger.info(e.getMessage());
        }
        if(rows!=null){
            return "redirect:product_select?product_id="+""+"&product_name="+""+"&pageSize="+pageSize+"&pageNumber="+pageNumber/*+"&status="+1*/;
        }else{
            return "error";
        }
    }
    //跳转到添加商品信息页面
    @RequestMapping("product-insert-page")
    public String insertProductPage(){
        return "product-insert";
    }
    //新增员工信息
    @RequestMapping(value = "product-insert")
    @ResponseBody
    public Map<String, Object> insertProduct(String name, Double buyprice, Double salesprice, Long stockamount, Long salesamount){
        //商品编号，若存在该商品编号，则插入成功，默认为null，表示不成功
        String productNumber = null;
            //把用户信息封装在类Product中
            Product product = new Product();
            product.setProductname(name);
            product.setProductbuyprice(buyprice);
            product.setProductsalesprice(salesprice);
            Stock stock = new Stock();
            stock.setStockamount(stockamount);
            stock.setSalesamount(salesamount);
            product.setStock(stock);
        try {
            Long productId = productServiceImpl.insertProduct(product);
            //利用productId查找productNumber
            Product product1 = productServiceImpl.selectProduct(productId);
            productNumber = product1.getProductnumber();
            //日志记录
            Logger logger = Logger.getLogger(ProductController.class);
            logger.info("插入数据成功，插入商品名称:"+name);
        } catch (Exception e) {
            //e.printStackTrace();
            Logger logger = Logger.getLogger(ProductController.class);
            logger.info("插入数据失败");
        }
        //转为json字符串后在发送
        Map<String,Object> map = new HashMap<>();
        map.put("productNumber",productNumber);
        return map;
    }
    //跳往修改员工信息页面
    @RequestMapping("/product-update")
    public String updateProductInfo(Model model,Long productId,String productNumber,String productName,Double productSalesPrice,Double productBuyPrice,Long stockAmount,Long salesAmount){
            Product product = new Product();
            product.setProductnumber(productNumber);
            product.setProductname(productName);
            product.setProductsalesprice(productSalesPrice);
            product.setProductbuyprice(productBuyPrice);
            Stock stock = new Stock();
            stock.setStockamount(stockAmount);
            stock.setSalesamount(salesAmount);
            stock.setProductid(productId);
            product.setStock(stock);
            model.addAttribute("product",product);
        return "product-update";
    }
    //处理商品信息修改
    @RequestMapping("product-update-after")
    @ResponseBody
    public Map<String,Object> employeeUpdate(String number,String name,Double buyprice,Double salesprice,Long stockamount,Long salesamount,Long productid){
            Integer row = null;
            Product product = new Product();
            product.setProductnumber(number);
            product.setProductname(name);
            product.setProductbuyprice(buyprice);
            product.setProductsalesprice(salesprice);
            Stock stock = new Stock();
            stock.setProductid(productid);
            stock.setStockamount(stockamount);
            stock.setSalesamount(salesamount);
            product.setStock(stock);
            try {
                row = productServiceImpl.updateProduct(product);
            } catch (RuntimeException e) {
                //e.printStackTrace();
                Logger logger = Logger.getLogger(ProductController.class);
                logger.info("修改数据失败:"+e.getMessage());
            }
            Map<String,Object> map = new HashMap<>();
            map.put("row",row);
            return map;
    }
}
