package com.eventmanegementSystem.event_manegement.services.JWT;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService {

    UserDetailsService userDetailService();
}
