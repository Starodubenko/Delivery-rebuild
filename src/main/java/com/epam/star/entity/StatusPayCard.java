package com.epam.star.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "status_card")
public class StatusPayCard extends AbstractEntity {

    @Column(name = "status_name")
    private String statusName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatusPayCard)) return false;

        StatusPayCard that = (StatusPayCard) o;

        if (!statusName.equals(that.statusName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return statusName.hashCode();
    }

    public String getStatusName() {

        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
