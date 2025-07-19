package org.example.javabt1.service;

import org.example.javabt1.dto.ResponseDto;
import org.example.javabt1.dto.request.CategoryRequest;
import org.example.javabt1.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<ResponseDto<Object>> addCategory(CategoryRequest categoryRequest);
    ResponseEntity<ResponseDto<Object>> updateCategory(Long id, CategoryRequest categoryRequest);
    ResponseEntity<ResponseDto<Object>> deleteCategory(Long id);
    ResponseEntity<ResponseDto<Object>> getCategory(Long id);
    ResponseEntity<ResponseDto<List<Category>>> getAll(int page, int size);
}
