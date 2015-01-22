package com.epam.star.entity;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "users")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType=DiscriminatorType.STRING)
public class Client extends AbstractEntity {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String telephone;
    private String mobilephone;
    @Column(name = "virtual_Balance")
    private BigDecimal virtualBalance;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position role;
    private Integer avatar;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "discount")
    private Discount discount;

    public Client() {
    }

    public Client(int id, String address, Integer avatar, Discount discount, String firstName, String lastName, String login, String middleName, String mobilephone, String password, Position role, String telephone, BigDecimal virtualBalance) {
        super(id);
        this.address = address;
        this.avatar = avatar;
        this.discount = discount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.middleName = middleName;
        this.mobilephone = mobilephone;
        this.password = password;
        this.role = role;
        this.telephone = telephone;
        this.virtualBalance = virtualBalance;
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getAddress() {
        return address;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getMobilephone() {
        return mobilephone;
    }
    public Position getRole() {
        return role;
    }
    public BigDecimal getVirtualBalance() {
        return virtualBalance;
    }
    public Integer getAvatar() {
        return avatar;
    }
    public Discount getDiscount() {
        return discount;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
    public void setRole(Position role) {
        this.role = role;
    }
    public void setVirtualBalance(BigDecimal virtualBalance) {
        this.virtualBalance = virtualBalance;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }
}
