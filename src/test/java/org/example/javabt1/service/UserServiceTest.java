package org.example.javabt1.service;

import org.example.javabt1.dto.ResponseDto;
import org.example.javabt1.dto.request.UserRequest;
import org.example.javabt1.entity.User;
import org.example.javabt1.enums.Role;
import org.example.javabt1.repositoty.UserRepository;
import org.example.javabt1.service.servicesimpl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserRequest userRequest;

    @BeforeEach
    void setup() {
        userRequest = UserRequest.builder()
                .username("Hoang Quang")
                .password("12345678")
                .phone("0964889734")
                .address("Hà Nội")
                .role(Role.ADMIN)
                .build();
    }

    @Test
    void testRegisterUser_Success() {
        // Giả sử username chưa tồn tại
        Mockito.when(userRepository.existsByUsername("Hoang Quang")).thenReturn(false);
        // Giả lập password đã mã hóa
        Mockito.when(passwordEncoder.encode("12345678")).thenReturn("encoded_password");

        // Gọi service
        ResponseDto<?> result = userService.registerUser(userRequest).getBody();

        // Kiểm tra mã phản hồi và thông báo
        Assertions.assertEquals(1000, result.getCode());
        Assertions.assertEquals("Bạn đã đăng ký tài khoản thành công", result.getMessage());

        User user = (User) result.getData();

        Assertions.assertEquals("Hoang Quang", user.getUsername());
        Assertions.assertEquals("encoded_password", user.getPassword());
        Assertions.assertEquals("0964889734", user.getPhone());
    }

}
