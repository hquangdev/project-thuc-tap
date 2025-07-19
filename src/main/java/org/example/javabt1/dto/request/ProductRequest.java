package org.example.javabt1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.example.javabt1.entity.Category;

@Data
public class ProductRequest {

    @NotBlank(message = "Không được bỏ trống")
    private String name;
    private String description;
    private int quantity;
    private Double price;
    private Long categoryId;
}
