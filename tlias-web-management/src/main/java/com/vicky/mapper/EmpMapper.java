package com.vicky.mapper;

import com.vicky.pojo.Dept;
import com.vicky.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     查询员工总数
     */
    @Select("select count(*) from emp")
    public Long count();

    //分页查询获取列表数据
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start,Integer pageSize);

    //员工信息查询
    //@Select("select * from emp") //插件实现分页
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    //批量删除员工id
    void delete(List<Integer> ids);

    //新增员工
    @Insert("insert into Emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    //查询员工
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    //更新员工
    void update(Emp emp);

    //登录
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    //根据部门id删除员工
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
