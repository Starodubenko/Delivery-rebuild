package com.epam.star.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "discount")
public class Discount extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "discount_percentage")
    private int percentage;

    public Discount() {
    }

    public Discount(String name, int percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }
    public int getPercentage() {
        return percentage;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;
        if (!super.equals(o)) return false;

        Discount discount = (Discount) o;

        if (percentage != discount.percentage) return false;
        if (!name.equals(discount.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + percentage;
        return result;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "name='" + name + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
