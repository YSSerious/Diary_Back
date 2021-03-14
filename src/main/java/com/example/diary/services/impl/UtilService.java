package com.example.diary.services.impl;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UtilService {

    private final static List<Pair<String, List<Integer>>> TIME_LINES = Arrays.asList(
            Pair.of("00:00 - 01:59", Arrays.asList(0, 1)),
            Pair.of("02:00 - 03:59", Arrays.asList(2, 3)),
            Pair.of("04:00 - 05:59", Arrays.asList(4, 5)),
            Pair.of("06:00 - 07:59", Arrays.asList(6, 7)),
            Pair.of("08:00 - 09:59", Arrays.asList(8, 9)),
            Pair.of("10:00 - 11:59", Arrays.asList(10, 11)),
            Pair.of("12:00 - 13:59", Arrays.asList(12, 13)),
            Pair.of("14:00 - 15:59", Arrays.asList(14, 15)),
            Pair.of("16:00 - 17:59", Arrays.asList(16, 17)),
            Pair.of("18:00 - 19:59", Arrays.asList(18, 19)),
            Pair.of("20:00 - 21:59", Arrays.asList(20, 21)),
            Pair.of("22:00 - 23:59", Arrays.asList(22, 23))
    );

    public static List<Pair<String, List<Integer>>> getTimeLInes() {
        return TIME_LINES;
    }

    public static List<String> getTimeLInesForUI() {
        return TIME_LINES.stream().map(Pair::getLeft).collect(Collectors.toList());
    }
}
