package com.eventmanegementSystem.event_manegement.services.event;


import com.eventmanegementSystem.event_manegement.DTO.EventDto;
import com.eventmanegementSystem.event_manegement.Entity.Event;
import com.eventmanegementSystem.event_manegement.repository.EventRepository;
import com.eventmanegementSystem.event_manegement.repository.UserRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepostory userRepository;

    public Event createEvent(EventDto eventDto) {
        Event event = new Event();
        event.setEventName(eventDto.getEventName());
        event.setLocation(eventDto.getLocation());
        event.setEventDateTime(eventDto.getEventDateTime());

        // Assign singer
        userRepository.findById(eventDto.getAssignedSingerId()).ifPresent(event::setAssignedSinger);

        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event updateEvent(Long id, EventDto eventDto) {
        return eventRepository.findById(id).map(event -> {
            event.setEventName(eventDto.getEventName());
            event.setLocation(eventDto.getLocation());
            event.setEventDateTime(eventDto.getEventDateTime());
            userRepository.findById(eventDto.getAssignedSingerId()).ifPresent(event::setAssignedSinger);
            return eventRepository.save(event);
        }).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getEventsBySingerId(Long singerId) {
        return eventRepository.findByAssignedSingerId(singerId);
    }

    // Filter events by date range and optionally by singer ID
    public List<Event> filterEventsByDateAndSinger(LocalDateTime startDate, LocalDateTime endDate, Long singerId) {
        if (singerId != null) {
            return eventRepository.findByEventDateTimeBetweenAndAssignedSingerId(startDate, endDate, singerId);
        } else {
            return eventRepository.findByEventDateTimeBetween(startDate, endDate);
        }
    }



    public List<Event> filterEventsByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return eventRepository.findByEventDateTimeBetween(startDate, endDate);
    }
}

