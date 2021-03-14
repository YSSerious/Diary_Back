package com.example.diary.services;

import com.example.diary.dtos.FoodDayInfo;
import com.example.diary.dtos.FoodVolumeInfo;
import com.example.diary.entity.Food;
import com.example.diary.entity.FoodRecord;

import java.util.List;
import java.util.Map;

public interface FoodService {
    Food getById(Long id);
    Map<String, List<Food>> getAll();
    List<FoodVolumeInfo> getFoodDayTimeLineInfo(List<String> foodRecordsIds);
    FoodDayInfo getFoodDayInfo(List<FoodRecord> foodRecords);
}
