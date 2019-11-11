package com.fork.cache.service;

import com.fork.cache.bean.Employee;
import com.fork.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Cacheable(value = "emp"/*,keyGenerator = "myKeyGenerator",condition = "#a0>1"*/)
    public Employee getEmp(Integer id){
        System.out.println("查询"+id + "号员工");
        Employee emp = employeeMapper.getEmpById(id);
        System.out.println("git测试");
        return emp;
    }
}
