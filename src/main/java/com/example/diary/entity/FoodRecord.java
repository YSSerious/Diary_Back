package com.example.diary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "foodrecord")
public class FoodRecord extends Record{

    @OneToMany(cascade = CascadeType.ALL, mappedBy="foodRecord")
    private List<FoodVolume> foodVolumes;
}
