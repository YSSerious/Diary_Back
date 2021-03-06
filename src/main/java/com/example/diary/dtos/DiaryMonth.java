package com.example.diary.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryMonth {
    private String name;
    private List<MonthDaysGeneralInfo> generalInfos;
    private List<MonthTimeLine> timeLines;
}
