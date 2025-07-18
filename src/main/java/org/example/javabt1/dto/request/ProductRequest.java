package org.example.javabt1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Không được bỏ trống")
    private String name;
    private String description;
    private Double price;
}
