package com.vicky.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vicky.mapper.EmpMapper;
import com.vicky.pojo.Emp;
import com.vicky.pojo.PageBean;
import com.vicky.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

/*    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //获取总记录
        Long count = empMapper.count();
        //获取分页查询结果
        Integer start = (page-1) *pageSize;
        List<Emp> empList = empMapper.page(start, pageSize);
        //封装pageBean
        PageBean pageBean = new PageBean(count,empList);
        return pageBean;
    }*/
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender,
                         LocalDate begin, LocalDate end) {
        //设置分页参数
        PageHelper.startPage(page, pageSize);
        //执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList; //需要强转为page类型
        //封装pageBean
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }
    //根据id查询员工
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    //更新员工
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
