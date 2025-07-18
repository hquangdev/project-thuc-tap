package org.example.javabt1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.javabt1.dto.ResponseDto;
import org.example.javabt1.dto.request.UserRequest;
import org.example.javabt1.dto.response.UserResponse;
import org.example.javabt1.enums.Role;
import org.example.javabt1.service.servicesimpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserServiceImpl userService;

    private UserRequest userRequest;
    private UserResponse userResponse;

    @BeforeEach
    void innitData() {
         userRequest = UserRequest.builder()
                .username("Hoang Quanggg")
                .password("12345678")
                .phone("0964889734")
                .address("Hà Nội")
                .role(Role.ADMIN)
                .build();

         userResponse = UserResponse.builder()
                .id(1L)
                .username("Hoang Quang")
                .password("12345678")
                .phone("0964889734")
                .address("Hà Nội")
                .role(Role.ADMIN)
                .createDate(LocalDate.of(2025,9,8))
                .updateDate(LocalDate.of(2025,9,8))
                .build();

    }

    @Test
    void addUser() throws Exception {
        //GVIEN
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String content = mapper.writeValueAsString(userRequest);

        // Mock ResponseDto<Object>
        ResponseDto<Object> responseDto = ResponseDto.builder()
                .code(1000)
                .message("Đăng ký thành công")
                .data(userResponse)
                .build();

        // Mock service
        Mockito.when(userService.registerUser(ArgumentMatchers.any()))
                .thenReturn(ResponseEntity.ok(responseDto));

        // Gửi request và kiểm tra response
        mvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("1000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("Hoang Quang"));

        log.info("addUser");
    }
}
