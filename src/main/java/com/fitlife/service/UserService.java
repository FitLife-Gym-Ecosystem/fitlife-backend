package com.fitlife.service;

import com.fitlife.dto.UserCreationRequest;
import com.fitlife.dto.UserResponse;
import com.fitlife.entity.Role;
import com.fitlife.entity.User;
import com.fitlife.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(UserCreationRequest request) {
        // 1. Check if user already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists"); // Sẽ học Global Exception sau
        }

        // 2. Map DTO to Entity (Tạm thời lưu password gốc, BCrypt sẽ học ở bài Security)
        User newUser = User.builder()
                .username(request.getUsername())
                .passwordHash(request.getPassword())
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();

        // 3. Save to Database
        User savedUser = userRepository.save(newUser);

        // 4. Map Entity back to DTO Response (Hide password)
        return UserResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .role(savedUser.getRole().name())
                .build();
    }
}