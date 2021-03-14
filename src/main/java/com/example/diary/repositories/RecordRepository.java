package com.example.diary.repositories;

import com.example.diary.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, String> {

    List<Record> findAllByZoneDateTimeAfterAndZoneDateTimeBefore(ZonedDateTime dateStart, ZonedDateTime dateFinish);
}
