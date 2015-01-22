package com.epam.star.entity;

import java.math.BigDecimal;

//@Entity
//@Table(name = "users")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public class Employee extends Client {
    private String identityCard;
    private String workBook;
    private String RNN;
    private String SIK;

    public Employee(int id, String address, Integer avatar, Discount discount, String firstName, String lastName, String login, String middleName, String mobilephone, String password, Position role, String telephone, BigDecimal virtualBalance, String identityCard, String RNN, String SIK, String workBook) {
        super(id, address, avatar, discount, firstName, lastName, login, middleName, mobilephone, password, role, telephone, virtualBalance);
        this.identityCard = identityCard;
        this.RNN = RNN;
        this.SIK = SIK;
        this.workBook = workBook;
    }

    public Employee() {

    }

    public String getSIK() {
        return SIK;
    }
    public String getRNN() {
        return RNN;
    }
    public String getWorkBook() {
        return workBook;
    }
    public String getIdentityCard() {
        return identityCard;
    }

    public void setSIK(String SIK) {
        this.SIK = SIK;
    }
    public void setRNN(String RNN) {
        this.RNN = RNN;
    }
    public void setWorkBook(String workBook) {
        this.workBook = workBook;
    }
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
}
