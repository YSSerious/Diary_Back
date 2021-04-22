package com.example.diary.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "weightrecord")
public class WeightRecord extends Record{
    //volume in kilograms.
    @Column(name = "volume")
    private double volume;

    @Override
    public String toString() {
        return "WeightRecord{" +
                "volume=" + volume +
                "} " + super.toString();
    }
}
