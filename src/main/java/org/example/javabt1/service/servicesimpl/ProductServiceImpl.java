package org.example.javabt1.service.servicesimpl;

import lombok.RequiredArgsConstructor;
import org.example.javabt1.dto.ResponseBuilder;
import org.example.javabt1.dto.ResponseDto;
import org.example.javabt1.dto.request.ProductRequest;
import org.example.javabt1.dto.response.ProductResponse;
import org.example.javabt1.entity.Product;
import org.example.javabt1.enums.Message;
import org.example.javabt1.repositoty.ProductRepository;
import org.example.javabt1.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ResponseEntity<ResponseDto<Object>> addProduct(ProductRequest productRequest) {
        if(productRepository.existsByName(productRequest.getName())) {
            throw new RuntimeException("Đã tồn tại tên sản phẩm này");
        }
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        productRepository.save(product);
        return ResponseBuilder.okResponse(Message.ADD_PRODUCT_SUCCESS, product);
    }

    @Override
    public ResponseEntity<ResponseDto<Object>> updateProduct(Long id, ProductRequest updatedProduct) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm id: " + id));

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());

        productRepository.save(product);
        return ResponseBuilder.okResponse(Message.UPDATE_SUCCESS, product);
    }

    @Override
    public ResponseEntity<ResponseDto<Object>> deleteProduct(Long id) {
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm id: " + id));
        productRepository.deleteById(id);
        return ResponseBuilder.okResponse(Message.DELETE_SUCCESS, null);
    }

    @Override
    public ResponseEntity<ResponseDto<Object>> getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm id: " + id));

        ProductResponse productOptional = new ProductResponse(product);
        return ResponseBuilder.okResponse(Message.GET_PRODUCT_SUCCESS, productOptional);
    }
    
    
    @Override
    public ResponseEntity<ResponseDto<List<Product>>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);

        return ResponseBuilder.okResponseWithPage(Message.GET_PRODUCT_SUCCESS, productPage);
    }



}
