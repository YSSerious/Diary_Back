package com.example.diary.controllers;

import com.example.diary.dtos.DayInfo;
import com.example.diary.dtos.DiaryMonth;
import com.example.diary.dtos.FoodVolumeInfo;
import com.example.diary.entity.BodyResponseRecord;
import com.example.diary.entity.Food;
import com.example.diary.entity.FoodRecord;
import com.example.diary.entity.GymRecord;
import com.example.diary.entity.Record;
import com.example.diary.entity.WaterRecord;
import com.example.diary.entity.WeightRecord;
import com.example.diary.enums.BodyResponse;
import com.example.diary.services.FoodService;
import com.example.diary.services.impl.RecordServiceImpl;
import com.example.diary.services.impl.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/food")
@CrossOrigin
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    @Qualifier("recordServiceImpl")
    private RecordServiceImpl recordService;

    @GetMapping("/getRecords")
    public List<DiaryMonth> getRecords(@RequestParam(value = "year") int year) {
        return recordService.getRecords(year);
    }

    @GetMapping("/getBodyResponseValues")
    public BodyResponse[] getBodyResponseValues() {
        return BodyResponse.values();
    }

    @GetMapping("/getTimeLInes")
    public List<String> getTimeLInes() {
        return UtilService.getTimeLInesForUI();
    }

    @GetMapping("/getFoodCatalog")
    public Map<String, List<Food>> getFoodCatalog() {
        return foodService.getAll();
    }

    @PostMapping("/getFoodDayTimeLineInfo")
    public List<FoodVolumeInfo> getFoodDayTimeLineInfo(@RequestBody List<String> foodRecordsIds) {
        return foodService.getFoodDayTimeLineInfo(foodRecordsIds);
    }

    @GetMapping("/getDayInfo")
    public DayInfo getDayInfo(@RequestParam(value = "year") int year,
                              @RequestParam(value = "month") Month month,
                              @RequestParam(value = "day") int day) {
        return recordService.getDayInfo(year, month, day);
    }

    @PostMapping("/createFoodRecord")
    public Record createFoodRecord(@RequestBody FoodRecord record) {
            record.getFoodVolumes().forEach(fv -> {
                Food food = foodService.getById(fv.getFood().getId());
                fv.setFood(food);
                fv.setFoodRecord(record);
            });
        return recordService.saveRecord(record);
    }

    @PostMapping("/createGymRecord")
    public Record createGymRecord(@RequestBody GymRecord record) {
        return recordService.saveRecord(record);
    }

    @PostMapping("/createWaterRecord")
    public Record createWaterRecord(@RequestBody WaterRecord record) {
        return recordService.saveRecord(record);
    }

    @PostMapping("/createBodyResponseRecord")
    public Record createBodyResponseRecord(@RequestBody BodyResponseRecord record) {
        return recordService.saveRecord(record);
    }

    @PostMapping("/createWeightRecord")
    public Record createWeightRecord(@RequestBody WeightRecord record) {
        return recordService.saveRecord(record);
    }
}
