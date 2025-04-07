package com.vicky.service;


import com.vicky.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查询全部部门数据
    List<Dept> list();

    //删除部门
    void delete(Integer id);

    //新增部门
    void add(Dept dept);

    //修改部门
    void update(Dept dept);

    //根据id查询
    Dept get(Integer id);
}
