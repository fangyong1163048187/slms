package com.haiwen.service;

import com.haiwen.mapper.EmployeeMapper;
import com.haiwen.pojo.Employee;
import com.haiwen.pojo.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

public interface EmployeeService {
    /**
     * 查找员工信息
     * @param employeeNumber
     * @param employeeName
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageInfo selectEmployee(String employeeNumber, String employeeName, Integer pageSize, Integer pageNumber);

    /**
     * 删除一个员工信息
     * @param employeeNumber
     * @return
     */
    Integer deleteOneEmployee(String employeeNumber);

    /**
     * 插入一个员工信息
     * @param employee
     * @return 返回主键值
     */
    Long insertEmployee(Employee employee);

    /**
     * 通过员工ID查找一个员工信息
     * @param id
     * @return
     */
    Employee selectEmployee(Long id);

    /**
     * 清空
     * @return
     */
    Integer deleteAll();

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    Integer updateEmployee(Employee employee);
}
