package com.haiwen.service.impl;

import com.haiwen.mapper.ProductMapper;
import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.Product;
import com.haiwen.pojo.Stock;
import com.haiwen.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Override
    public PageInfo selectProduct(String productNumber, String productName, Integer pageSize, Integer pageNumber) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setProductNumber(productNumber);
        pageInfo.setProductName(productName);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageStart((pageNumber-1)*pageSize);
        //查不到结果时，list<?>类型返回一个实例化对象，大小为0，即调用size时为0
        List<Product> products = productMapper.selectByProNumAndPsd(productNumber, productName, pageInfo);
        pageInfo.setList(products);
        //查找到的符合条件的员工总人数
        Integer countOfProduct = productMapper.selectCountOfProduct(productNumber,productName);
        //计算总页数
        Integer pageTotal = ((countOfProduct%pageSize)==0)?(countOfProduct/pageSize):(countOfProduct/pageSize+1);
        //当员工人数为0时，总页数为1
        if(countOfProduct==0){
            pageTotal = 1;
        }
        //把员工总人数放入pageInfo对象
        pageInfo.setPageCountOfProduct(countOfProduct);
        //把总页数放入pageInfo对象
        pageInfo.setPageTotal(pageTotal);
        return pageInfo;
    }
    //通过商品编号删除一个商品信息,返回受影响的行数
    @Override
    //子类抛了异常，父类也要抛异常
    public Integer deleteOneProduct (@Param("productNumber")String productNumber,@Param("stockProductID")Long stockProductID) throws RuntimeException{
        Integer row = productMapper.deleteOneProduct(productNumber,stockProductID);
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
        Integer rows = productMapper.deleteAll();
        if(rows==0){
            throw new java.lang.RuntimeException("清空数据失败");
        }
        return rows;
    }
    //插入员工信息
    @Override
    public Long insertProduct(Product product) throws RuntimeException{
        Long productId = null;
        Integer row = productMapper.insertProduct(product);
        //获取插入的记录的主键ID
        productId = product.getId();
        //向子表插入信息
        Stock stock = product.getStock();
        stock.setProductid(productId);
        Integer isSuccess = productMapper.insertStockProductId(stock);
        //row等于0表示插入父表信息失败了，才会返回0,isSuccess等于0表示插入子表信息失败
        if(row==0 || isSuccess==0){
            throw new RuntimeException("插入数据失败");
        }
        return productId;
    }
    //通过商品ID查找商品编号
    @Override
    public Product selectProduct(Long id) {
        Product product = productMapper.selectProduct(id);
        return product;
    }
    //修改商品数据
    @Override
    public Integer updateProduct(Product product) throws RuntimeException{
        Integer is = productMapper.judgeHavaDataInSonTable(product.getStock().getProductid());
        Integer row = productMapper.updateProduct(product,is);
        if(row==0){
            throw new RuntimeException("修改商品数据失败");
        }
        return row;
    }
}
