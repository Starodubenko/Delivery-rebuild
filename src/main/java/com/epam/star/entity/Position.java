package com.epam.star.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "positions")
public class Position extends AbstractEntity {

    @Column(name = "position_name")
    private String positionName;


    public Position() {
    }

    public Position(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {

        return positionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (positionName != null) {
            if (!(position.positionName.equals(positionName)))
                return false;
        } else {
            if (!(position.positionName == null))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        if (positionName != null) return positionName.hashCode();
        else return 0;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionName='" + positionName + '\'' +
                '}';
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
