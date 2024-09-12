package com.eventmanegementSystem.event_manegement.DTO;

import com.eventmanegementSystem.event_manegement.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private UserRole userRole;
    private Long userId;
}
