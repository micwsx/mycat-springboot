package com.enjoy.dao;

import com.enjoy.domain.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDetailDao {
    @Insert({
            "insert into order_detail(id,orderId,item,price,remark)",
            " values(#{id},#{orderId},#{item},#{price},#{remark})"
    })
    int add(OrderDetail order);

    // 批量插入操作
    @Insert({"<script>",
            "insert into order_detail(id,orderId,item,price,remark) values",
            "<foreach collection='list' item='it' separator=','>",
            "(#{it.id},#{it.orderId},#{it.item},#{it.price},#{it.remark})",
            "</foreach>",
            "</script>"})
    void batchInsert(List<OrderDetail> list);

    @Select("select * from order_detail")
    List<OrderDetail> selectAll();


    @Select("select * from order_detail where id=#{id}")
    OrderDetail selectById(Long id);
}
