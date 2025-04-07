package com.vicky.controller;

import com.vicky.pojo.Emp;
import com.vicky.pojo.PageBean;
import com.vicky.pojo.Result;
import com.vicky.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    //默认参数
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){ //必须与前端接口文档大小一致
        log.info("分页查询，参数{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        //调用service方法查询
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作，ids{}",ids);
        empService.delete(ids);
        return Result.success();
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }
    //根据id查询员工
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询员工：{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }
    //根据id更新员工
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
