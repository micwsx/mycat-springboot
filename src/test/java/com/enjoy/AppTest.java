package com.enjoy;

import static org.junit.Assert.assertTrue;

import com.enjoy.domain.Order;
import com.enjoy.domain.OrderDetail;
import com.enjoy.domain.User;
import com.enjoy.service.AtomikosXAService;
import com.enjoy.service.OrderService;
import com.enjoy.service.UserService;
import com.enjoy.util.SnowflakeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AtomikosXAService atomikosXAService;


    ///////////////User表操作///////////////////////////////
    @Test
    public void addUser() {
        User user = new User();
        user.setId(SnowflakeUtil.nextId());
        user.setName("Jack");
        userService.add(user);
    }

    @Test
    public void getUsers() {
        List<User> list = userService.getUsers();
        list.forEach(user -> {
            System.out.println(user);
        });
    }

    // 批量插入
    @Test
    public void batchAdd() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new User(SnowflakeUtil.nextId(), "Michael" + i));
        }
        userService.batchAdd(list);
    }

    // 全局序列号批量插入
    @Test
    public void batchSequenceInsert() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new User(0L, "Jack" + i));
        }
        userService.batchSequenceInsert(list);
    }

    ///////////////Order表操作///////////////////////////////
    @Test
    public void addOrder(){
        for (int i = 0; i < 2; i++) {
            Order order=new Order();
            order.setId(SnowflakeUtil.nextId());
            order.setProduct("Books");
            order.setProvince("Shanghai");
            order.setCreated(new Date());
            OrderDetail orderDetail=new OrderDetail(SnowflakeUtil.nextId(),order.getId(),"Old man",new BigDecimal(12.9),"firt edition");
            order.getOrderDetails().add(orderDetail);
            int result=orderService.add(order);
            System.out.println(result);
        }
    }

    @Test
    public void batchAddOrder(){
        List<Order> ordres=new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Order order=new Order();
            order.setId(SnowflakeUtil.nextId());
            order.setProduct("Books"+i);
            order.setProvince("Shanghai");
            order.setCreated(new Date());
            ordres.add(order);
        }
        orderService.batchAdd(ordres);
    }

    /**
     *  如果t_order表和order_detail表不是ER表，则查询结果有可能为空。
     */
    @Test
    public void getOrders(){
        List<Order> orders=orderService.getOrders();
        orders.forEach(order->{
            System.out.println(order);
        });
    }

    /**
     * 如果t_order表和order_detail表不是ER表，在SQL脚本前加入注解，则才查询完整数据。
     */
    @Test
    public void getOrdersDifferentInDB(){
        List<Order> orders=orderService.getAllDifferDb();
        orders.forEach(order->{
            System.out.println(order+"-->"+order.getOrderDetails().get(0).toString());
        });
    }

    /**
     * 多数据源事务操作
     */
//    @Test
//    public void atomikosTest(){
//        atomikosXAService.transfer(300);
//    }
}
