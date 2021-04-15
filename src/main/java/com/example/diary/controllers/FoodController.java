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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/food")
@CrossOrigin
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    @Qualifier("recordServiceImpl")
    private RecordServiceImpl recordService;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/getRecords")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<DiaryMonth> getRecords(@RequestParam(value = "year") int year) {
        return recordService.getRecords(year);
    }

    @GetMapping("/getBodyResponseValues")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public BodyResponse[] getBodyResponseValues() {
        return BodyResponse.values();
    }

    @GetMapping("/getTimeLInes")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<String> getTimeLInes() {
        return UtilService.getTimeLInesForUI();
    }

    @GetMapping("/getFoodCatalog")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Map<String, List<Food>> getFoodCatalog() {
        return foodService.getAll();
    }

    @PostMapping("/getFoodDayTimeLineInfo")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<FoodVolumeInfo> getFoodDayTimeLineInfo(@RequestBody List<String> foodRecordsIds) {
        return foodService.getFoodDayTimeLineInfo(foodRecordsIds);
    }

    @GetMapping("/getDayInfo")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public DayInfo getDayInfo(@RequestParam(value = "year") int year,
                              @RequestParam(value = "month") Month month,
                              @RequestParam(value = "day") int day) {
        return recordService.getDayInfo(year, month, day);
    }

    @GetMapping("/getMonthInfo")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<DayInfo> getMonthInfo(@RequestParam(value = "year") int year,
                              @RequestParam(value = "month") Month month,
                                @RequestParam(value = "fromDay", required = false) Integer fromDay,
                                @RequestParam(value = "toDay", required = false) Integer toDay) {
        return recordService.getMonthInfo(year, month, fromDay, toDay);
    }

    @PostMapping("/createFoodRecord")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Record createFoodRecord(@RequestBody FoodRecord record) {
            record.getFoodVolumes().forEach(fv -> {
                Food food = foodService.getById(fv.getFood().getId());
                fv.setFood(food);
                fv.setFoodRecord(record);
            });
        return recordService.saveRecord(record);
    }

    @PostMapping("/createGymRecord")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Record createGymRecord(@RequestBody GymRecord record) {
        return recordService.saveRecord(record);
    }

    @PostMapping("/createWaterRecord")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Record createWaterRecord(@RequestBody WaterRecord record) {
        return recordService.saveRecord(record);
    }

    @PostMapping("/createBodyResponseRecord")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Record createBodyResponseRecord(@RequestBody BodyResponseRecord record) {
        return recordService.saveRecord(record);
    }

    @PostMapping("/createWeightRecord")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Record createWeightRecord(@RequestBody WeightRecord record) {
        return recordService.saveRecord(record);
    }
}
