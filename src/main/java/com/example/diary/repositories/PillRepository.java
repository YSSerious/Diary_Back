package com.example.diary.repositories;

import com.example.diary.entity.Pill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PillRepository extends JpaRepository<Pill, Long> {
}
