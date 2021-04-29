package com.enjoy.dao;

import com.enjoy.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {

    @Insert("insert into user(id,name) values(#{id},#{name})")
    int add(User user);

    // 批量插入操作
    @Insert({"<script>",
            "insert into user(id,name) values",
            "<foreach collection='list' item='item' separator=','>",
            "(#{item.id},#{item.name})",
            "</foreach>",
            "</script>"})
    void batchInsert(List<User> list);

    // 全局序列号批量插入操作,全局序列号字段不用写
    @Insert({"<script>",
            "/*!mycat:catlet=io.mycat.route.sequence.BatchInsertSequence */",
            "insert into user(name) values",
            "<foreach collection='list' item='item' separator=','>",
            "(#{item.name})",
            "</foreach>",
            "</script>"})
    void batchSequenceInsert(List<User> list);

    @Select("select * from user")
    List<User> selectAll();
}
