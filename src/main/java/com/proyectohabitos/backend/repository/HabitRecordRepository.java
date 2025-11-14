package com.proyectohabitos.backend.repository;

import com.proyectohabitos.backend.model.HabitRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRecordRepository extends JpaRepository<HabitRecord, Long> {
    List<HabitRecord> findByMemberFamilyId(Long familyId);
}
