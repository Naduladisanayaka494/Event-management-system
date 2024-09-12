package com.eventmanegementSystem.event_manegement.DTO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventDto {

    private Long id;
    private String eventName;
    private String location;
    private LocalDateTime eventDateTime;
    private Long assignedSingerId;
}
