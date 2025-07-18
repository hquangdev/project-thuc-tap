package org.example.javabt1.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {
    private String token;
    private String role;
    private String username;
    private String address;
}
