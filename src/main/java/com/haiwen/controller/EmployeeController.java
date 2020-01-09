package com.haiwen.controller;

import com.haiwen.pojo.Employee;
import com.haiwen.pojo.PageInfo;
import com.haiwen.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.acl.LastOwnerException;
import java.sql.Date;
import java.sql.SQLData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeeController {
    @Resource
    private EmployeeService employeeServiceImpl;
    private Employee e;
    //跳转到员工信息页面
    @RequestMapping("employee-info")
    public String emploeeInfo(){
        return "employee-info";
    }
    //查找员工信息
    @RequestMapping("employee_select")
    public String selectEmployeeInfo(@RequestParam("employee_id")String employeeId, @RequestParam("employee_name")String employeeName, Model model,@RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize,@RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber/*,String status*/){
        PageInfo pageInfo = employeeServiceImpl.selectEmployee(employeeId, employeeName, pageSize, pageNumber);
        //把每页的员工信息存入model对象中，model对象在request域中可见
        model.addAttribute("employee",pageInfo);
        /*model.addAttribute("status",status);*/
        return "employee-info";
    }
    //删除一个员工信息
    @RequestMapping("employee_delete_one")
    //employeeNumber为要删除的员工编号，employeeId为要查找的员工编号
    public String deleteOneEmployeeInfo(String employeeNumber,@RequestParam("employee_id")String employeeId, @RequestParam("employee_name")String employeeName,@RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize,@RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        Integer row = employeeServiceImpl.deleteOneEmployee(employeeNumber);
        if(row>0){
            //删除成功
            Logger logger = Logger.getLogger(EmployeeController.class);
            logger.info("删除数据成功，删除员工姓名:"+employeeName);
            return "redirect:employee_select?employee_id="+employeeId
                    +"&employee_name="+employeeName+"&pageSize="+pageSize+"&pageNumber="+pageNumber;
        }else{
            //出错了
            return "login";
        }
    }
    //清空所有员工信息
    @RequestMapping("employee_truncate")
    public String employeeTruncate(@RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize,@RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        //返回受影响的行数
        Integer rows = employeeServiceImpl.deleteAll();
        if(rows>0){
            return "redirect:employee_select?employee_id="+""+"&employee_name="+""+"&pageSize="+pageSize+"&pageNumber="+pageNumber/*+"&status="+1*/;
        }else{
            return "error";
        }
    }

    //跳转到添加员工信息页面
    @RequestMapping("employee-insert-page")
    public String insertEmployeePage(){
        return "employee-insert";
    }
    //新增员工信息
    @RequestMapping(value = "employee-insert")
    @ResponseBody
    public Map<String, Object> insertEmployee(String name, String phone, String email, String address, String sex, String birthday){
        //出生日期格式有由YYYY-MM-DDTHH:mm转为YYYY-MM-DD,然后转为sql的date类型
        String newBirthday = birthday.substring(0, birthday.indexOf("T"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //插入是否成功的标志，默认为null，表示不成功
        String employeeNumber = null;
        try {
            java.util.Date parseDate = simpleDateFormat.parse(newBirthday);
            java.sql.Date date = new Date(parseDate.getTime());
            //把用户信息封装在类Employee中
            Employee employee = new Employee();
            employee.setEmployeename(name);
            employee.setEmployeephone(phone);
            employee.setEmployeeemail(email);
            employee.setEmployeeaddress(address);
            employee.setEmployeesex(sex);
            employee.setEmployeebirthday(date);
            //调用service层方法,返回主键ID
            Long employeeId = employeeServiceImpl.insertEmployee(employee);
            //利用employeeId查找employeeNumber
            Employee employee1 = employeeServiceImpl.selectEmployee(employeeId);
            employeeNumber = employee1.getEmployeenumber();
            //日志记录
            Logger logger = Logger.getLogger(EmployeeController.class);
            logger.info("插入数据成功，插入员工姓名:"+name);
        } catch (Exception e1) {
            //拦截器拦截该异常并记录到日志当中
            Logger logger = Logger.getLogger(EmployeeController.class);
            logger.info("插入数据失败，失败原因:"+e1.getMessage());
        }
        //转为json字符串后在发送
        Map<String,Object> map = new HashMap<>();
        map.put("employeeNumber",employeeNumber);
        return map;
    }
    //跳往修改员工信息页面
    @RequestMapping("/employee-update")
    public String updateEmploeeInfo(Model model,String employeeNumber,String employeeName,String employeeSex,String employeePhone,String employeeAddress,String employeeBirthday,String employeeEmail){
        try {
            Employee employee = new Employee();
            employee.setEmployeenumber(employeeNumber);
            employee.setEmployeename(employeeName);
            employee.setEmployeesex(employeeSex);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = simpleDateFormat.parse(employeeBirthday);
            java.sql.Date sqlDate = new Date(date.getTime());
            employee.setEmployeebirthday(sqlDate);
            employee.setEmployeeaddress(employeeAddress);
            employee.setEmployeeemail(employeeEmail);
            employee.setEmployeephone(employeePhone);
            model.addAttribute("employee",employee);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return "employee-update";
    }
    //处理员工信息修改
    @RequestMapping("employee-update-after")
    @ResponseBody
    public Integer employeeUpdate(String number,String name,String sex,String email,String address,String phone,String birthday){
        Integer integer = 0;
        try {
            Employee employee = new Employee();
            employee.setEmployeenumber(number);
            employee.setEmployeename(name);
            employee.setEmployeephone(phone);
            employee.setEmployeeemail(email);
            employee.setEmployeeaddress(address);
            //出生日期格式有由YYYY-MM-DDTHH:mm转为YYYY-MM-DD,然后转为sql的date类型
            String newBirthday = birthday.substring(0, birthday.indexOf("T"));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parseDate = simpleDateFormat.parse(newBirthday);
            employee.setEmployeebirthday(new Date(parseDate.getTime()));
            employee.setEmployeesex(sex);
            integer = employeeServiceImpl.updateEmployee(employee);
        } catch (ParseException e1) {
           // e1.printStackTrace();
            Logger logger = Logger.getLogger(EmployeeController.class);
            logger.info("日期格式错误:"+e1.getMessage());
        }
        return integer;
    }
}
