package com.epam.star.entity;

import javax.persistence.*;

@Entity
@Table(name = "ordered_goods")
public class OrderedGoods extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order2 order;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "goods_id")
    private Goods goods;
    @Column(name = "goods_count")
    private int goodsCount;


    public OrderedGoods() {
    }

    public Order2 getOrder() {
        return order;
    }

    public void setOrder(Order2 order) {
        this.order = order;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Order2 getOrderNumber() {
        return order;
    }

    public void setOrderNumber(Order2 order) {
        this.order = order;
    }
}
