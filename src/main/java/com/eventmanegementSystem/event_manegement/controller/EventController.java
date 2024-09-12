package com.eventmanegementSystem.event_manegement.controller;


import com.eventmanegementSystem.event_manegement.DTO.EventDto;
import com.eventmanegementSystem.event_manegement.Entity.Event;
import com.eventmanegementSystem.event_manegement.services.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody EventDto eventDto) {
        return new ResponseEntity<>(eventService.createEvent(eventDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(event -> new ResponseEntity<>(event, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        return new ResponseEntity<>(eventService.updateEvent(id, eventDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/singer/{singerId}")
    public ResponseEntity<List<Event>> getEventsBySingerId(@PathVariable Long singerId) {
        return new ResponseEntity<>(eventService.getEventsBySingerId(singerId), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Event>> filterEventsByDateAndSinger(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate,
            @RequestParam(value = "singerId", required = false) Long singerId) {

        List<Event> events = eventService.filterEventsByDateAndSinger(startDate, endDate, singerId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
