package com.example.diary.services;

import com.example.diary.dtos.DayInfo;
import com.example.diary.dtos.DiaryMonth;
import com.example.diary.dtos.DiaryMonthWrapper;
import com.example.diary.entity.Record;

import java.time.Month;
import java.util.List;

public interface RecordService {
    Record saveRecord(Record record);
    List<DiaryMonthWrapper> getYearRecords(int year, String userId);
    DiaryMonth getMonthRecords(int year, Month month, String userId);
    DayInfo getDayInfo(int year, Month month, int day);
    List<DayInfo> getMonthInfo(int year, Month month, Integer fromDay, Integer toDay);
}
