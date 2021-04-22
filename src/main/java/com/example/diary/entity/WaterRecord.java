package com.example.diary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "waterrecord")
public class WaterRecord extends Record{
    //volume in milliliters.
    @Column
    private int volume;

    @Override
    public String toString() {
        return "WaterRecord{" +
                "volume=" + volume +
                "} " + super.toString();
    }
}
