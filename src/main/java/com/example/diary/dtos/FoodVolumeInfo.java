package com.example.diary.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodVolumeInfo {
    private List<FoodRecordInfo> foodRecordInfos;
    private ZonedDateTime zonedDateTime;
}
