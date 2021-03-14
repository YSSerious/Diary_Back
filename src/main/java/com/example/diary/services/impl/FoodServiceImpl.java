package com.example.diary.services.impl;

import com.example.diary.dtos.FoodDayInfo;
import com.example.diary.dtos.FoodRecordInfo;
import com.example.diary.dtos.FoodVolumeInfo;
import com.example.diary.entity.Food;
import com.example.diary.entity.FoodRecord;
import com.example.diary.entity.FoodVolume;
import com.example.diary.entity.Record;
import com.example.diary.exceptions.NotFoundException;
import com.example.diary.repositories.FoodRecordRepository;
import com.example.diary.repositories.FoodRepository;
import com.example.diary.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodRecordRepository foodRecordRepository;

    @Override
    public Food getById(Long id) {
        return foodRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Food with %s not found", id)));
    }

    @Override
    public Map<String, List<Food>> getAll() {
        return foodRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(food -> food.getFoodCategory().getId()))
                .collect(Collectors.groupingBy(food -> food.getFoodCategory().getName(), LinkedHashMap::new, toList()));
    }

    @Override
    public List<FoodVolumeInfo> getFoodDayTimeLineInfo(List<String> foodRecordsIds) {
        List<FoodRecord> foodRecords = foodRecordRepository.findAllById(foodRecordsIds);
        return foodRecords.stream()
                .sorted(Comparator.comparing(Record::getZoneDateTime))
                .map(record ->
                        FoodVolumeInfo.builder()
                        .foodRecordInfos(getFoodRecordInfos(record.getFoodVolumes()))
                        .zonedDateTime(record.getZoneDateTime())
                        .build())
                .collect(toList());
    }

    private List<FoodRecordInfo> getFoodRecordInfos(List<FoodVolume> foodVolumes) {
        return foodVolumes.stream().map(fv -> {
            Food food = fv.getFood();
            return FoodRecordInfo.builder()
                    .foodName(fv.getFood().getName())
                    .kilocalories(getFoodValue(food.getKilocalories(), fv.getVolume(), food.isInGrams()))
                    .proteins(getFoodValue(food.getProteins(), fv.getVolume(), food.isInGrams()))
                    .carbohydrates(getFoodValue(food.getCarbohydrates(), fv.getVolume(), food.isInGrams()))
                    .fats(getFoodValue(food.getFats(), fv.getVolume(), food.isInGrams()))
                    .foodHealthy(food.getFoodHealthy())
                    .build();
        }).collect(toList());
    }

    @Override
    public FoodDayInfo getFoodDayInfo(List<FoodRecord> foodRecords) {
        List<FoodRecordInfo> fris = foodRecords.stream()
                .flatMap(foodRecord -> getFoodRecordInfos(foodRecord.getFoodVolumes()).stream())
                .collect(toList());
        return FoodDayInfo.builder()
                .kilocalories(fris.stream().mapToDouble(FoodRecordInfo::getKilocalories).sum())
                .proteins(fris.stream().mapToDouble(FoodRecordInfo::getProteins).sum())
                .carbohydrates(fris.stream().mapToDouble(FoodRecordInfo::getCarbohydrates).sum())
                .fats(fris.stream().mapToDouble(FoodRecordInfo::getFats).sum())
                .build();
    }

    //@volume - how much was eaten. May be in grams or pieces, based on @inGrams.
    private double getFoodValue(double baseValue, int volume, boolean inGrams) {
        //volume in grams or pieces based on inGrams marker
        return inGrams ? (volume * baseValue) / 100 : baseValue * volume;
    }
}
