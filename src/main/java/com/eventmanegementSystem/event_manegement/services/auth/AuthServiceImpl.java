package com.eventmanegementSystem.event_manegement.services.auth;


import com.eventmanegementSystem.event_manegement.DTO.SignUpRequest;
import com.eventmanegementSystem.event_manegement.DTO.UserDto;
import com.eventmanegementSystem.event_manegement.Entity.User;
import com.eventmanegementSystem.event_manegement.enums.UserRole;
import com.eventmanegementSystem.event_manegement.repository.UserRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepostory userRepository;

    @Override
    public UserDto createdCustomer(SignUpRequest signuprequest) {
        User user = new User();
        user.setName(signuprequest.getName());
        user.setEmail(signuprequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signuprequest.getPassword()));
        user.setUserRole(UserRole.SINGER);
        User createduser  =userRepository.save(user);
        UserDto userdto = new UserDto();
        userdto.setId(createduser.getId());
        return userdto;
    }

    @Override
    public boolean hascustomerwithemail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserDto> getAllStudents() {
        List<User> students = userRepository.findByUserRole(UserRole.SINGER);
        return students.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setUserRole(user.getUserRole());
        return dto;
    }


}
