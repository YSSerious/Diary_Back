package com.example.diary.entity;

import com.example.diary.enums.FoodHealthy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "foodvolume")
public class FoodVolume {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 64)
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="food_id", nullable=false)
    private Food food;

    //how much was eaten, grams or pieces
    @Column
    private int volume;

    @ManyToOne
    @JoinColumn(name="foodrecord_id", nullable=false)
    @JsonIgnore
    private FoodRecord foodRecord;
}
