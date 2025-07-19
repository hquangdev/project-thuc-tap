package org.example.javabt1.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javabt1.dto.ResponseDto;
import org.example.javabt1.dto.request.ProductRequest;
import org.example.javabt1.dto.response.ProductResponse;
import org.example.javabt1.service.servicesimpl.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProductController {
    private final ProductServiceImpl productService;

    @Operation(summary = "Add Product", description = "Thêm sản phẩm mới")
    @PostMapping
    public ResponseEntity<ResponseDto<Object>> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @Operation(summary = "Update Product", description = "Cập nhật sản phẩm")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> updateProduct(@PathVariable Long id,@RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Product", description = "Xóa sản phẩm")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @Operation(summary = "Get All Product", description = "Lấy danh sách sản phẩm")
    @GetMapping()
    public ResponseEntity<ResponseDto<List<ProductResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getAll(page, size);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get Product", description = "lấy 1 sản phẩm")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
}
