package com.vicky.service.impl;

import com.vicky.mapper.DeptMapper;
import com.vicky.mapper.EmpMapper;
import com.vicky.pojo.Dept;
import com.vicky.pojo.DeptLog;
import com.vicky.service.DeptLogService;
import com.vicky.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class) //交给spring事务管理
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.deleteById(id); //根据id删除部门
/*            int i = 1/0;*/
            empMapper.deleteByDeptId(id);//根据部门id删除部门员工
        } finally { //无论如何，都可以正常记录日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行解散部门的操作，此次解散"+id+"号部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateById(dept);
    }

    @Override
    public Dept get(Integer id) {
        return deptMapper.getById(id);
    }
}
