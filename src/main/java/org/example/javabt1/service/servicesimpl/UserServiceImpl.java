package org.example.javabt1.service.servicesimpl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.example.javabt1.dto.ResponseBuilder;
import org.example.javabt1.dto.ResponseDto;
import org.example.javabt1.dto.request.LoginRequest;
import org.example.javabt1.dto.request.UserRequest;
import org.example.javabt1.dto.response.AuthenticationResponse;
import org.example.javabt1.dto.response.UserResponse;
import org.example.javabt1.entity.User;
import org.example.javabt1.enums.Message;
import org.example.javabt1.repositoty.UserRepository;
import org.example.javabt1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    protected static final String SINGER_KEY = "LDzDIQeFNWXeWjG/yQ3yQz0cNJY2QAx08t5vyu7/E1ZaYZNsaSzaQBQ/nhWH591f";


    @Override
    public ResponseEntity<ResponseDto<Object>> registerUser(UserRequest userRequest) {
        try {
            if(userRepository.existsByUsername(userRequest.getUsername())) {
                throw new RuntimeException("Đã tồn tại tên người dùng này!");
            }
            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setAddress(userRequest.getAddress());
            user.setPhone(userRequest.getPhone());
            user.setRole(userRequest.getRole());
            userRepository.save(user);

            return ResponseBuilder.okResponse(Message.REGISTER_SUCCESS, user);
        } catch (Exception e) {
            throw new RuntimeException("Đã có lỗi xảy ra: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ResponseDto<Object>> getAll() {
        List<User> users = userRepository.findAll();

        return ResponseBuilder.okResponse(Message.GET_ALL_USER_SUCCESS, users);
    }

    @Override
    public ResponseEntity<ResponseDto<Object>> getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng id: " + id));
        UserResponse userResponse = new UserResponse(user);
        return ResponseBuilder.okResponse(
                Message.GET_ALL_USER_SUCCESS,
                userResponse
        );
    }

    @Override
    public ResponseEntity<ResponseDto<Object>> updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng id: " + id));
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setAddress(userRequest.getAddress());
        user.setPhone(userRequest.getPhone());
        user.setRole(userRequest.getRole());
        userRepository.save(user);

        return ResponseBuilder.okResponse(Message.UPDATE_USER_SUCCESS, user);
    }

    @Override
    public ResponseEntity<ResponseDto<Object>> deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng id: " + id));
        userRepository.delete(user);
        return ResponseBuilder.okResponse(
                Message.DELETE_SUCCESS,
                null
        );
    }

    @Override
    public ResponseEntity<ResponseDto<Object>> login(LoginRequest loginRequest) {
        try {
            User user = userRepository.findByUsername(loginRequest.getUsername())
                    .filter(u -> passwordEncoder.matches(loginRequest.getPassword(), u.getPassword()))
                    .orElseThrow(() -> new RuntimeException("Tài khoản hoặc mật khẩu không đúng"));

            // 3. Tạo token
            String token = generateToken(user);

            return ResponseBuilder.okResponse(
                    Message.LOGIN_SUCCESS,
                    AuthenticationResponse.builder()
                            .token(token)
                            .role(user.getRole().toString())
                            .username(user.getUsername())
                            .address(user.getAddress())
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Đăng nhập thất bại" + e.getMessage());
        }
    }

    private String generateToken(User user) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("giang.com")
                .claim("scope", buildScope(user))
                .expirationTime(new Date(Instant.now().plus(7, ChronoUnit.DAYS).toEpochMilli()))
                .issueTime(new Date())
                .build();

        Payload payload = new Payload(jwtClaimSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        jwsObject.sign(new MACSigner(SINGER_KEY.getBytes()));
        return jwsObject.serialize();

    }

    private String buildScope(User user) {
        if (user.getRole() != null) {
            return "ROLE_" + user.getRole().name();
        }
        return "";
    }

}
