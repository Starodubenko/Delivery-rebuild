package com.epam.star.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Pay_card")
public class PayCard extends AbstractEntity {

    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "secret_number")
    private String secretNumber;
    private BigDecimal balance;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status_pay_card")
    private StatusPayCard statusPayCard;

    public PayCard() {
    }

    public StatusPayCard getStatusPayCard() {
        return statusPayCard;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public String getSecretNumber() {
        return secretNumber;
    }
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setStatusPayCard(StatusPayCard statusPayCard) {
        this.statusPayCard = statusPayCard;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public void setSecretNumber(String secretNumber) {
        this.secretNumber = secretNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
