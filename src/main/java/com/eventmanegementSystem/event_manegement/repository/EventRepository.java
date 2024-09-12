package com.eventmanegementSystem.event_manegement.repository;


import com.eventmanegementSystem.event_manegement.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByAssignedSingerId(Long singerId);

    List<Event> findByEventDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Event> findByEventDateTimeBetweenAndAssignedSingerId(LocalDateTime startDate, LocalDateTime endDate, Long assignedSingerId);

  // For filtering by date only// Filter by date
}
