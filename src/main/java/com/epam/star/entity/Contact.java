package com.epam.star.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact extends AbstractEntity {
    private String owner;
    private String telephone;

    public Contact() {
    }

    public String getOwner() {
        return owner;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
