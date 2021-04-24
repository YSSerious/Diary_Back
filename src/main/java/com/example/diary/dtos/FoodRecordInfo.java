package com.example.diary.dtos;

import com.example.diary.enums.FoodHealthy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FoodRecordInfo {
    private String foodName;
    private double kilocalories;
    private double proteins;
    private double carbohydrates;
    private double fats;
    private FoodHealthy foodHealthy;
}
