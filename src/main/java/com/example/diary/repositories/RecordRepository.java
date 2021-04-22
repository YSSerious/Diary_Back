package com.example.diary.repositories;

import com.example.diary.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, String> {

    List<Record> findAllByZoneDateTimeAfterAndZoneDateTimeBefore(ZonedDateTime dateStart, ZonedDateTime dateFinish);

    @Query("select r from Record r where year(r.zoneDateTime) = ?1 and month(r.zoneDateTime) = ?2 and r.user.id = ?3")
    List<Record> findByYearAndMonth(int year, int month, String userId);

    @Query("select count(r) > 0 from Record r where year(r.zoneDateTime) = ?1 and month(r.zoneDateTime) = ?2 and r.user.id = ?3")
    boolean isExistByYearAndMonth(int year, int month, String userId);
}
