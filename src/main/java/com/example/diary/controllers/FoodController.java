package com.example.diary.controllers;

import com.example.diary.dtos.DayInfo;
import com.example.diary.dtos.DiaryMonth;
import com.example.diary.dtos.DiaryMonthWrapper;
import com.example.diary.dtos.FoodVolumeInfo;
import com.example.diary.entity.BodyResponseRecord;
import com.example.diary.entity.Food;
import com.example.diary.entity.FoodRecord;
import com.example.diary.entity.GymRecord;
import com.example.diary.entity.Pill;
import com.example.diary.entity.PillRecord;
import com.example.diary.entity.Record;
import com.example.diary.entity.WaterRecord;
import com.example.diary.entity.WeightRecord;
import com.example.diary.enums.BodyResponse;
import com.example.diary.services.FoodService;
import com.example.diary.services.PillService;
import com.example.diary.services.impl.RecordServiceImpl;
import com.example.diary.services.impl.UtilService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);

    @Autowired
    private FoodService foodService;

    @Autowired
    @Qualifier("recordServiceImpl")
    private RecordServiceImpl recordService;

    @Autowired
    private PillService pillService;

    @GetMapping("/publicContent")
    public String allAccess() {
        return "FoodController Public Content.";
    }

    @GetMapping("/getYearRecords")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<DiaryMonthWrapper> getYearRecords(@RequestParam(value = "year") int year,
                                                  @RequestParam(value = "userId") String userId) {
        logger.info("Getting year records. Year: {}", year);
        return recordService.getYearRecords(year, userId);
    }

    @GetMapping("/getMonthRecords")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public DiaryMonth getMonthRecords(@RequestParam(value = "year") int year,
                                      @RequestParam(value = "month") Month month,
                                      @RequestParam(value = "userId") String userId) {
        logger.info("Getting month records. Year: {}, Month: {}", year, month);
        return recordService.getMonthRecords(year, month, userId);
    }

    @GetMapping("/getBodyResponseValues")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public BodyResponse[] getBodyResponseValues() {
        logger.info("Getting body response values");
        return BodyResponse.values();
    }

    @GetMapping("/getTimeLInes")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<String> getTimeLInes() {
        logger.info("Getting time lines");
        return UtilService.getTimeLInesForUI();
    }

    @GetMapping("/getFoodCatalog")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Map<String, List<Food>> getFoodCatalog() {
        logger.info("Getting food catalog");
        return foodService.getAll();
    }

    @GetMapping("/getPillCatalog")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Pill> getPillCatalog() {
        logger.info("Getting pill catalog");
        return pillService.getAll();
    }

    @PostMapping("/getFoodDayTimeLineInfo")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<FoodVolumeInfo> getFoodDayTimeLineInfo(@RequestBody List<String> foodRecordsIds) {
        logger.info("Getting food day/timeline info. foodRecordsIds: {}", foodRecordsIds);
        List<FoodVolumeInfo> foodVolumeInfos = foodService.getFoodDayTimeLineInfo(foodRecordsIds);
        logger.info("Food day/timeline volumes: {}", foodVolumeInfos);
        return foodVolumeInfos;
    }

    @GetMapping("/getDayInfo")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public DayInfo getDayInfo(@RequestParam(value = "year") int year,
                              @RequestParam(value = "month") Month month,
                              @RequestParam(value = "day") int day) {
        logger.info("Getting day info. Year: {}, Month: {}, Day: {}", year, month, day);
        return recordService.getDayInfo(year, month, day);
    }

    @GetMapping("/getMonthInfo")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<DayInfo> getMonthInfo(@RequestParam(value = "year") int year,
                                      @RequestParam(value = "month") Month month,
                                      @RequestParam(value = "fromDay", required = false) Integer fromDay,
                                      @RequestParam(value = "toDay", required = false) Integer toDay) {
        logger.info("Getting month info. Year: {}, Month: {}, From day: {}, To day: {}", year, month, fromDay, toDay);
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
        logger.info("Creating Food Record: {}", record);
        return recordService.saveRecord(record);
    }

    @PostMapping("/createGymRecord")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Record createGymRecord(@RequestBody GymRecord record) {
        logger.info("Creating Gym Record: {}", record);
        return recordService.saveRecord(record);
    }

    @PostMapping("/createWaterRecord")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Record createWaterRecord(@RequestBody WaterRecord record) {
        logger.info("Creating Water Record: {}", record);
        return recordService.saveRecord(record);
    }

    @PostMapping("/createBodyResponseRecord")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Record createBodyResponseRecord(@RequestBody BodyResponseRecord record) {
        if (StringUtils.isWhitespace(record.getNote())) {
            record.setNote(null);
        }
        logger.info("Creating Body Response Record: {}", record);
        return recordService.saveRecord(record);
    }

    @PostMapping("/createWeightRecord")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Record createWeightRecord(@RequestBody WeightRecord record) {
        logger.info("Creating Weight Record: {}", record);
        return recordService.saveRecord(record);
    }

    @PostMapping("/createPillRecord")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Record createPillRecord(@RequestBody PillRecord record) {
        record.getPillVolumes().forEach(pv -> {
            Pill pill = pillService.getById(pv.getPill().getId());
            pv.setPill(pill);
            pv.setPillRecord(record);
        });
        logger.info("Creating Pill Record: {}", record);
        return recordService.saveRecord(record);
    }
}
