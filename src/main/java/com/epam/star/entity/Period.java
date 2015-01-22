package com.epam.star.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "period")
public class Period extends AbstractEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Date period;
    @Column(name = "period_name")
    private String periodName;

    public Period() {
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Period)) return false;
        if (!super.equals(o)) return false;

        Period period1 = (Period) o;

        if (!period.equals(period1.period)) return false;
        if (!periodName.equals(period1.periodName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + period.hashCode();
        result = 31 * result + periodName.hashCode();
        return result;
    }
}
