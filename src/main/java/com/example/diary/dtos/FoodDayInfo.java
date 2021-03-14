package com.example.diary.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDayInfo {
    private double kilocalories;
    private double proteins;
    private double carbohydrates;
    private double fats;
}
