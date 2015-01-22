package com.epam.star.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "goods")
public class Goods extends AbstractEntity {

    @Column(name = "goods_name")
    private String goodsName;
    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "image")
    private Image image;
    @Column(name = "price")
    private BigDecimal price;

//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "cart_id")
//    private Cart cart;
    @Transient
    private boolean inCart;

    public Goods() {
    }

    public boolean isInCart() {
        return inCart;
    }
    public Image getImage() {
        return image;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public String getGoodsName() {
        return goodsName;
    }
//    public Cart getCart() {
//        return cart;
//    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Goods)) return false;

        Goods goods = (Goods) o;

        if (inCart != goods.inCart) return false;
        if (goodsName != null ? !goodsName.equals(goods.goodsName) : goods.goodsName != null) return false;
        if (image != null ? !image.equals(goods.image) : goods.image != null) return false;
        if (price != null ? !price.equals(goods.price) : goods.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = image != null ? image.hashCode() : 0;
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (inCart ? 1 : 0);
        return result;
    }
}
