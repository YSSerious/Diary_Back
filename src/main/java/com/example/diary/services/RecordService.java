package com.example.diary.services;

import com.example.diary.dtos.DayInfo;
import com.example.diary.dtos.DiaryMonth;
import com.example.diary.entity.Record;

import java.time.Month;
import java.util.List;

public interface RecordService {
    Record saveRecord(Record record);
    List<DiaryMonth> getRecords(int year);
    DayInfo getDayInfo(int year, Month month, int day);
}
