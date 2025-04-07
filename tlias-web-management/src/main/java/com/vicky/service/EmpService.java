package com.vicky.service;

import com.vicky.pojo.Emp;
import com.vicky.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {
    //进行分页查询
    PageBean page(Integer page, Integer pageSize,String name, Short gender,
                  LocalDate begin, LocalDate end);

    //实现批量删除
    void delete(List<Integer> ids);

    //新增员工
    void save(Emp emp);

    //查询员工
    Emp getById(Integer id);

    //更新员工
    void update(Emp emp);

    //登录
    Emp login(Emp emp);
}
