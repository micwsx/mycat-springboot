package com.enjoy.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private String product;
    private String province;
    private Date created;

    private List<OrderDetail> orderDetails;

    public Order() {
        orderDetails=new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", province='" + province + '\'' +
                ", created=" + created +
                ", orderDetails=" + orderDetails.size() +
                '}';
    }
}
