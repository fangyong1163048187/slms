package com.haiwen.mapper;

import com.haiwen.pojo.Employee;
import com.haiwen.pojo.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    /**
     * 通过员工编号和姓名查找员工信息
     * @param employeeNumber 为""时，不查找该条件
     * @param employeeName 为""时，不查找该条件
     * @param pageInfo 分页查找
     * @return 返回员工信息
     */
    List<Employee> selectByEmpNumAndPsd(@Param("employeeNumber")String employeeNumber, @Param("employeeName")String employeeName, @Param("pageInfo")PageInfo pageInfo);

    /**
     * 查找符合条件的员工总人数
     * @return
     */
    Integer selectCountOfEmployee(@Param("employeeNumber")String employeeNumber, @Param("employeeName")String employeeName);

    /**
     * 删除一个员工信息
     * @param employeeNumber
     * @return
     */
    Integer deleteOneEmployee(@Param("employeeNumber")String employeeNumber);

    /**
     * 清空数据
     * @return
     */
    Integer deleteAll();

    /**
     * 插入一个员工信息
     * @param employee
     * @return
     */
    Integer insertEmployee(Employee employee);

    /**
     * 通过员工主键查找员工信息
     * @param id
     * @return
     */
    Employee selectEmployee(@Param("id")Long id);

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    Integer updateEmployee(Employee employee);
}
