package com.example.diary.services.impl;

import com.example.diary.entity.Pill;
import com.example.diary.exceptions.NotFoundException;
import com.example.diary.repositories.PillRepository;
import com.example.diary.services.PillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PillServiceImpl implements PillService {

    @Autowired
    private PillRepository pillRepository;

    @Override
    public Pill getById(Long id) {
        return pillRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Pill with %s not found", id)));
    }

    @Override
    public List<Pill> getAll() {
        return pillRepository.findAll();
    }
}
