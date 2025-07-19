package org.example.javabt1.dto.response;

import lombok.Data;
import org.example.javabt1.entity.Product;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private Double price;


    private Long categoryId;
    private String categoryName;
    private String categoryContent;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();

        if (product.getCategory() != null) {
            this.categoryId = product.getCategory().getId();
            this.categoryName = product.getCategory().getName();
            this.categoryContent = product.getCategory().getContent();
        }
    }
}
