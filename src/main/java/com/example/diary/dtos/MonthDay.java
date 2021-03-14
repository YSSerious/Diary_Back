package com.example.diary.dtos;

import com.example.diary.entity.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class MonthDay {
    private int dayNumber;
    private Map<String, List<Record>> records;
}
