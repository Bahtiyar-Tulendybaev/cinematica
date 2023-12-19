package com.example.cinematicaDemo.dao;

import com.example.cinematicaDemo.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRep extends JpaRepository<Schedule,Long> {
}
