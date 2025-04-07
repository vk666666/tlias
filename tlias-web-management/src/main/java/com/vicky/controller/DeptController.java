package com.vicky.controller;

import com.vicky.annotation.Log;
import com.vicky.pojo.Dept;
import com.vicky.pojo.Result;
import com.vicky.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//部门管理
@RestController
@Slf4j
@RequestMapping("/depts")
public class DeptController {
    //日志
//    private static Logger log = LoggerFactory.getLogger(DeptController.class);
    //注入service对象
    @Autowired
    private DeptService deptService;

    /**
     * 查询部门数据
     * @return
     */
//    @RequestMapping(value = "/depts",method = RequestMethod.GET) //指定请求方式为GET
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        //"log报错的，在设置里面搜索注解处理器，把default和底下的都改成从项目类路径获取处理器"

        //调用service查询部门数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList); //重载的success
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);
        //调用servie删除部门
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @return
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门：{}",dept);
        //新增部门
        deptService.add(dept);
        return Result.success(); //重载的success
    }

    /**
     * 修改部门
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门{}" + dept.getId()+ "为{}"+ dept);
        //根据id修改部门
        deptService.update(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        log.info("获取部门id{}" + id);
        //根据id查询部门
        Dept de = deptService.get(id);
        return Result.success(de);
    }
}