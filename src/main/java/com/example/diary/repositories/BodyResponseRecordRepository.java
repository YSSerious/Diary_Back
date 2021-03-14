package com.example.diary.repositories;

import com.example.diary.entity.BodyResponseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyResponseRecordRepository extends JpaRepository<BodyResponseRecord, String> {
}
