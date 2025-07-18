package org.example.javabt1.service;

import org.example.javabt1.dto.ResponseDto;
import org.example.javabt1.dto.request.LoginRequest;
import org.example.javabt1.dto.request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseDto<Object>> registerUser(UserRequest userRequest);

    ResponseEntity<ResponseDto<Object>> login(LoginRequest loginRequest);

    ResponseEntity<ResponseDto<Object>> getAll();

    ResponseEntity<ResponseDto<Object>> getById(Long id);

    ResponseEntity<ResponseDto<Object>> updateUser(Long id, UserRequest userRequest);

    ResponseEntity<ResponseDto<Object>> deleteUser(Long id);
}
