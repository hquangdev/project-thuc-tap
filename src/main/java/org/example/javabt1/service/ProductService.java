package org.example.javabt1.service;

import org.example.javabt1.dto.ResponseDto;
import org.example.javabt1.dto.request.ProductRequest;
import org.example.javabt1.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<ResponseDto<Object>> addProduct(ProductRequest productRequest);
    ResponseEntity<ResponseDto<Object>> updateProduct(Long id, ProductRequest productRequest);
    ResponseEntity<ResponseDto<Object>> deleteProduct(Long id);
    ResponseEntity<ResponseDto<Object>> getProduct(Long id);
    ResponseEntity<ResponseDto<List<Product>>> getAll(int page, int size);
}
