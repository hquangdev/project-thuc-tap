package org.example.javabt1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.javabt1.entity.User;
import org.example.javabt1.enums.Role;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String password;
    private String address;
    private String phone;
    private Role role;
    private LocalDate createDate;
    private LocalDate updateDate;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.role = user.getRole();
        this.createDate = user.getCreateDate();
        this.updateDate = user.getUpdateDate();
    }
}
