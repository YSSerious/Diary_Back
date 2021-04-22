package com.example.diary.services;

import com.example.diary.entity.Pill;

import java.util.List;

public interface PillService {
    Pill getById(Long id);
    List<Pill> getAll();
}
