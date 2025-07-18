package org.example.javabt1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javabt1.dto.ResponseDto;
import org.example.javabt1.dto.request.LoginRequest;
import org.example.javabt1.dto.request.UserRequest;
import org.example.javabt1.service.servicesimpl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class RegisterUserController {
    private final UserServiceImpl userService;

    @Operation(summary = "Register", description = "API đăng ký tài khoản")
    @PostMapping
    public ResponseEntity<ResponseDto<Object>> registerUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.registerUser(userRequest);
    }

    @Operation(summary = "Login", description = "Đăng nhập")
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Object>> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @Operation(summary = "get all user", description = "Lấy danh sách tài khoản")
    @GetMapping()
    public ResponseEntity<ResponseDto<Object>> listUser(){
        return userService.getAll();
    }

    @Operation(summary = "Get user", description = "Lấy một tài khoản theo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @Operation(summary = "Delete user", description = "xóa tài khoản")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @Operation(summary = "Update user", description = "cập nhật tài khoản")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

}
