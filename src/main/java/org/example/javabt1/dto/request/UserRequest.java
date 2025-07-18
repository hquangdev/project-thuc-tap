package org.example.javabt1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.example.javabt1.enums.Role;
import org.example.javabt1.validator.Capitalized;

@Data
@Builder
public class UserRequest {
    @NotBlank(message = "Username không được để trống")
    @Capitalized(message = "Username phải bắt đầu bằng chữ in hoa")
    private String username;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, message = "Password phải có ít nhất 6 ký tự")
    private String password;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10,11}$", message = "Số điện thoại không hợp lệ")
    private String phone;
    private Role role;
}
