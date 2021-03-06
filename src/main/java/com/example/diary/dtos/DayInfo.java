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
public class DayInfo {
    private int dayNumber;
    private FoodDayInfo food;
    private WaterDayInfo water;
    private BodyResponseInfo bodyResponse;
    private WeightInfo weight;
}
