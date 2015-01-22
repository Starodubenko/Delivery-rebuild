package com.epam.star.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_order")
public class Order2 extends AbstractEntity {

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Client user;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "order")
    private Set<OrderedGoods> goods = new HashSet<>();
    @Column(name = "ORDER_NUMBER")
    private int orderNumber;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "period_id")
    private Period period;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delivery_date")
    private Date deliveryDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "additional_info")
    private String additionalInfo;
    private boolean paid;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "discount_id")
    private Discount discount;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;
    private boolean cart;

    public boolean isCart() {
        return cart;
    }

    public void setCart(boolean cart) {
        this.cart = cart;
    }

    public Set<OrderedGoods> getGoods() {
        return goods;
    }

    public void setGoods(Set<OrderedGoods> goods) {
        this.goods = goods;
    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setCartNumber(int number) {
        this.orderNumber = number;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period periodId) {
        this.period = periodId;
    }


    public BigDecimal getTotalSum() {
        BigDecimal totalSum = new BigDecimal(0);

        for (OrderedGoods good : goods) {
            BigDecimal cost = good.getGoods().getPrice().multiply(new BigDecimal(good.getGoodsCount()));
            totalSum = totalSum.add(cost);
        }


        return totalSum;
    }

    public void addGoods(Goods goods) {
        OrderedGoods orderedGoods = new OrderedGoods();
        orderedGoods.setGoods(goods);
        orderedGoods.setGoodsCount(1);
        this.goods.add(orderedGoods);
    }

    public void removeGoods(Goods good) {
        for (OrderedGoods goodd : goods) {
            if (goodd.getGoods().equals(good)) {
                goods.remove(goodd);
            }
        }
    }

    public BigDecimal getCostByGoodsId(int id) {


        for (OrderedGoods good : goods) {
            if (good.getId() == id) {
                return good.getGoods().getPrice().multiply(new BigDecimal(good.getGoodsCount()));
            }
        }
        return null;
    }

    public void setGoodsCount(Goods good, int count) {
        for (OrderedGoods orderedGoods : goods) {
            if (orderedGoods.getGoods().equals(good)) {
                orderedGoods.setGoodsCount(count);
            }
        }
    }

    public void clear() {
        goods.clear();
    }

    public int getGoodsCount() {
        return goods.size();
    }
}
