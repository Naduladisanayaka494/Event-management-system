package com.eventmanegementSystem.event_manegement.Entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String location;

    private LocalDateTime eventDateTime;

    @ManyToOne
    @JoinColumn(name = "singer_id")
    private User assignedSinger; // The singer assigned to the event
}
