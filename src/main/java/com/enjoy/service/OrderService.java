package com.enjoy.service;

import com.enjoy.dao.OrderDao;
import com.enjoy.dao.OrderDetailDao;
import com.enjoy.domain.Order;
import com.enjoy.domain.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    public int add(Order order) {
        int result = 0;
        try {
            result = orderDao.add(order);
            List<OrderDetail> details = order.getOrderDetails();
            orderDetailDao.batchInsert(details);
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    public void batchAdd(List<Order> orders) {
        orderDao.batchInsert(orders);
    }

    /**
     * 跨库查询操作
     */
    public List<Order> getAllDifferDb() {
        return orderDao.selectAllDifferDb();
    }

    public List<Order> getOrders() {
        return orderDao.selectAll();
    }

}
