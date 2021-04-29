package com.enjoy.dao;

import com.enjoy.domain.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderDao {

    @Insert({
            "insert into `t_order`(id,product,province,created)",
            " values(#{id},#{product},#{province},now())"
    })
    int add(Order order);

    // 批量插入操作
    @Insert({"<script>",
            "/*!mycat:catlet=io.mycat.route.sequence.BatchInsertSequence */",
            "insert into `t_order`(product,province,created) values",
            "<foreach collection='list' item='item' separator=','>",
            "(#{item.product},#{item.province},now())",
            "</foreach>",
            "</script>"})
    void batchInsert(List<Order> list);

    // 跨库查询操作
    @Select({"<script>",
            "/*!mycat:catlet=io.mycat.catlets.ShareJoin */",
            "select a.id,a.product,a.province,a.created,b.id as detail_Id,b.orderId,b.item,b.price,b.remark ",
            "from t_order a inner join order_detail b on a.id=b.orderId",
            "</script>"})
    @Results({
            @Result(id = true ,column = "id",property = "id"),
            @Result(column = "product",property = "product"),
            @Result(column = "province",property = "province"),
            @Result(column = "created",property = "created"),
            @Result(column = "detail_Id",  property = "orderDetails",
                    many =@Many(select = "com.enjoy.dao.OrderDetailDao.selectById") ),
    })
    List<Order> selectAllDifferDb();

    @Select("select a.id,a.product,a.province,a.created," +
            "b.id as detail_Id,b.orderId,b.item,b.price,b.remark " +
            "from t_order a inner join order_detail b on a.id=b.orderId")
    List<Order> selectAll();
}
