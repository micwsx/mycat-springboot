package com.enjoy.domain;

import java.math.BigDecimal;

public class OrderDetail {

    private Long id;
    private Long orderId;
    private String item;
    private BigDecimal price;
    private String remark;

    public OrderDetail(Long id, Long orderId, String item, BigDecimal price, String remark) {
        this.id = id;
        this.orderId = orderId;
        this.item = item;
        this.price = price;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", item='" + item + '\'' +
                ", price=" + price +
                ", remark='" + remark + '\'' +
                '}';
    }
}
