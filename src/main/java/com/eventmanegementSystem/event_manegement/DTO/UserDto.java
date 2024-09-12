package com.eventmanegementSystem.event_manegement.DTO;

import com.eventmanegementSystem.event_manegement.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {



    private Long id;
    private String name;
    private String email;

    private UserRole userRole;

}
