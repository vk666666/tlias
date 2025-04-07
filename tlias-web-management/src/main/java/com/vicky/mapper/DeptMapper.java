package com.vicky.mapper;

import com.vicky.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    //查询全部部门数据
    @Select("select * from dept")
    List<Dept> list();

    //根据id删除部门数据
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    //新增部门
    @Insert("insert into dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    //修改
    @Update("update dept set name = #{name}, create_time = #{createTime},update_time = #{updateTime} where id = #{id}")
    void updateById(Dept dept);

    //根据id查询
    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);
}
