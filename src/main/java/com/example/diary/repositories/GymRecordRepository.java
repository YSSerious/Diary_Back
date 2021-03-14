package com.example.diary.repositories;

import com.example.diary.entity.GymRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRecordRepository extends JpaRepository<GymRecord, String> {
}
