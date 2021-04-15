package com.example.diary.services.impl;

import com.example.diary.dtos.DayInfo;
import com.example.diary.dtos.DiaryMonth;
import com.example.diary.dtos.MonthDay;
import com.example.diary.dtos.MonthDaysGeneralInfo;
import com.example.diary.dtos.MonthTimeLine;
import com.example.diary.dtos.WaterDayInfo;
import com.example.diary.dtos.WeightInfo;
import com.example.diary.entity.FoodRecord;
import com.example.diary.entity.Record;
import com.example.diary.entity.User;
import com.example.diary.entity.WaterRecord;
import com.example.diary.entity.WeightRecord;
import com.example.diary.repositories.RecordRepository;
import com.example.diary.repositories.UserRepository;
import com.example.diary.services.FoodService;
import com.example.diary.services.RecordService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service("recordServiceImpl")
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Record saveRecord(Record record) {
        User user = userRepository.getById(record.getUser().getId());
        record.setUser(user);
        return recordRepository.save(record);
    }

    @Override
    public List<DiaryMonth> getRecords(int year) {
        ZonedDateTime start = ZonedDateTime.now().withYear(year).with(firstDayOfYear()).withHour(0).withMinute(0).withSecond(0).withNano(0);
        ZonedDateTime end = ZonedDateTime.now().withYear(year).with(lastDayOfYear()).withHour(23).withMinute(59).withSecond(59);
        return convertDiaryMonths(recordRepository.findAllByZoneDateTimeAfterAndZoneDateTimeBefore(start, end));
    }

    @Override
    public DayInfo getDayInfo(int year, Month month, int day) {
        ZonedDateTime start = ZonedDateTime.now().withYear(year).withMonth(month.getValue()).withDayOfMonth(day).withHour(0).withMinute(0).withSecond(0).withNano(0);
        ZonedDateTime end = ZonedDateTime.now().withYear(year).withMonth(month.getValue()).withDayOfMonth(day).withHour(23).withMinute(59).withSecond(59);
        List<Record> dayRecords = recordRepository.findAllByZoneDateTimeAfterAndZoneDateTimeBefore(start, end);
        Map<Class, List<Record>> recordsByType = dayRecords.stream().collect(Collectors.groupingBy(Record::getClass));

        return DayInfo.builder()
                .dayNumber(day)
                .food(foodService.getFoodDayInfo(castRecord(recordsByType, FoodRecord.class)))
                .water(getWaterDayInfo(castRecord(recordsByType, WaterRecord.class)))
                .weight(getWeightInfo(castRecord(recordsByType, WeightRecord.class)))
                .build();
    }

    @Override
    public List<DayInfo> getMonthInfo(int year, Month month, Integer fromDay, Integer toDay) {
        int from = Optional.ofNullable(fromDay).orElse(1);
        YearMonth ym = getYearMonth(ZonedDateTime.now().withYear(year).withMonth(month.getValue()));
        int to = Optional.ofNullable(toDay).orElse(ym.lengthOfMonth()+1);

        return IntStream.range(from, to)
                .mapToObj(i -> getDayInfo(year, month, i))
                .collect(Collectors.toList());
    }

    private <T extends Record> List<T> castRecord(Map<Class, List<Record>> recordsByType, Class<T> clazz){
        return Optional.ofNullable(recordsByType.get(clazz))
                .orElse(new ArrayList<>())
                .stream()
                .map(clazz::cast)
                .collect(Collectors.toList());
    }

    private WaterDayInfo getWaterDayInfo(List<WaterRecord> waterRecords){
        return WaterDayInfo.builder()
                .volume(waterRecords.stream().mapToInt(WaterRecord::getVolume).sum())
                .build();
    }

    private WeightInfo getWeightInfo(List<WeightRecord> weightRecords){
        return WeightInfo.builder()
                .volume(weightRecords.stream().mapToDouble(WeightRecord::getVolume).average().orElse(0))
                .build();
    }

    private List<DiaryMonth> convertDiaryMonths(List<Record> records) {
        return records.stream().collect(Collectors.groupingBy(record -> record.getZoneDateTime().getMonth().name()))
                .entrySet().stream()
                .map(recordMap -> new DiaryMonth(recordMap.getKey(), getMonthDaysGeneralInfo(recordMap.getValue()), convertMonthTimeLines(recordMap.getValue())))
                .sorted(Comparator.comparing(dm -> Month.valueOf(dm.getName()).getValue()))
                .collect(Collectors.toList());
    }

    private List<MonthTimeLine> convertMonthTimeLines(List<Record> records) {
        return records.stream().collect(Collectors.groupingBy(record -> getTimeLine(record.getZoneDateTime())))
                .entrySet().stream()
                .map(recordMap -> new MonthTimeLine(recordMap.getKey(), convertMonthDays(recordMap.getValue()))).collect(Collectors.toList());
    }

    private List<MonthDay> convertMonthDays(List<Record> records) {
        return records.stream().collect(Collectors.groupingBy(record -> record.getZoneDateTime().getDayOfMonth()))
                .entrySet().stream()
                .map(recordMap -> new MonthDay(recordMap.getKey(), convertRecords(recordMap.getValue()))).collect(Collectors.toList());
    }

    private Map<String, List<Record>> convertRecords(List<Record> records) {
        return records.stream().collect(Collectors.groupingBy(record -> record.getClass().getSimpleName()));
    }

    private List<MonthDaysGeneralInfo> getMonthDaysGeneralInfo(List<Record> records) {
        ZonedDateTime anyDateOfMonthRecord = records.get(0).getZoneDateTime();
        YearMonth ym = getYearMonth(anyDateOfMonthRecord);
        return IntStream.range(1, ym.lengthOfMonth()+1)
                .mapToObj(i -> new MonthDaysGeneralInfo(i, LocalDate.of(anyDateOfMonthRecord.getYear(), anyDateOfMonthRecord.getMonth(), i).getDayOfWeek().name()))
                .collect(Collectors.toList());
    }

    YearMonth getYearMonth(ZonedDateTime zonedDateTime) {
        return YearMonth.from(zonedDateTime);
    }

    String getTimeLine(ZonedDateTime dateTime) {
        return UtilService.getTimeLInes()
                .stream()
                .filter(time -> time.getRight().contains(dateTime.getHour()))
                .map(Pair::getLeft).findFirst()
                .orElse("Unknown time");
    }
}
