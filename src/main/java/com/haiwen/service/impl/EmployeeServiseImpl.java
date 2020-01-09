package com.haiwen.service.impl;

import com.haiwen.mapper.EmployeeMapper;
import com.haiwen.pojo.Employee;
import com.haiwen.pojo.PageInfo;
import com.haiwen.service.EmployeeService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiseImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public PageInfo selectEmployee(String employeeNumber, String employeeName,Integer pageSize,Integer pageNumber) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setEmployeeNumber(employeeNumber);
        pageInfo.setEmployeeName(employeeName);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageStart((pageNumber-1)*pageSize);
        //查不到结果时，list<object>类型返回一个实例化对象，大小为0，即调用size时为0
        List<Employee> employees = employeeMapper.selectByEmpNumAndPsd(employeeNumber, employeeName, pageInfo);
        pageInfo.setList(employees);
        //查找到的符合条件的员工总人数
        Integer countOfEmployee = employeeMapper.selectCountOfEmployee(employeeNumber,employeeName);
        //计算总页数
        Integer pageTotal = ((countOfEmployee%pageSize)==0)?(countOfEmployee/pageSize):(countOfEmployee/pageSize+1);
        //当员工人数为0时，总页数为1
        if(countOfEmployee==0){
            pageTotal = 1;
        }
        //把员工总人数放入pageInfo对象
        pageInfo.setPageCountOfEmployee(countOfEmployee);
        //把总页数放入pageInfo对象
        pageInfo.setPageTotal(pageTotal);
        return pageInfo;
    }
//通过员工编号删除一个员工信息,返回受影响的行数
    @Override
    public Integer deleteOneEmployee(String employeeNumber) {
        Integer row = employeeMapper.deleteOneEmployee(employeeNumber);
        return row;
    }
    //插入员工信息
    @Override
    public Long insertEmployee(Employee employee) {
        Long employeeId = null;
        employeeMapper.insertEmployee(employee);
        //获取插入的记录的主键ID
        employeeId = employee.getId();
        return employeeId;
    }

    //通过员工ID查找员工编号
    @Override
    public Employee selectEmployee(Long id) {
        Employee employee = employeeMapper.selectEmployee(id);
        return employee;
    }

    //清空数据
    @Override
    /*@Pointcut("execution(* com.haiwen.service.impl.EmployeeServiseImpl.truncate())")*/
    public Integer deleteAll(){
        Integer rows = employeeMapper.deleteAll();
        return rows;
    }
    //修改员工数据
    @Override
    public Integer updateEmployee(Employee employee) {
        Integer integer = employeeMapper.updateEmployee(employee);
        return integer;
    }
}
