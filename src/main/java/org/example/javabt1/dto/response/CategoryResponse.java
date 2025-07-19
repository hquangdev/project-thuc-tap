package org.example.javabt1.dto.response;

import lombok.Data;
import org.example.javabt1.entity.Category;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String content;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.content = category.getContent();
    }
}
