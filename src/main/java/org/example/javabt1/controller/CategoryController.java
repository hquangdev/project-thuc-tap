package org.example.javabt1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javabt1.dto.ResponseDto;
import org.example.javabt1.dto.request.CategoryRequest;
import org.example.javabt1.entity.Category;
import org.example.javabt1.service.servicesimpl.CategoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @Operation(summary = "Add Category", description = "Thêm danh mục mới")
    @PostMapping
    public ResponseEntity<ResponseDto<Object>> addCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.addCategory(categoryRequest);
    }

    @Operation(summary = "Update Category", description = "Cập nhật sản phẩm")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> updateCategory(@PathVariable Long id,@RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategory(id, categoryRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Category", description = "Xóa danh mục")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    @Operation(summary = "Get All Category", description = "Lấy danh sách danh mục")
    @GetMapping()
    public ResponseEntity<ResponseDto<List<Category>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return categoryService.getAll(page, size);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get Category", description = "lấy 1 danh mục")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }
}
