package com.example.diary.entity;

import com.example.diary.enums.FoodHealthy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 20)
    private Long id;

    @Column
    private String name;

    @Column(name = "ingrams")
    //in grams per 100 grams of product or in pieces.
    private boolean inGrams;

    @Column(name = "defaultportion")
    //in grams.
    private Double defaultPortion;

    @Column
    private double kilocalories;

    @Column
    private double proteins;

    @Column
    private double carbohydrates;

    @Column
    private double fats;

    @Column(name = "foodhealthy")
    @Enumerated(EnumType.STRING)
    private FoodHealthy foodHealthy;

    @ManyToOne
    @JoinColumn(name="foodcategory_id", nullable=false)
    private FoodCategory foodCategory;
}
