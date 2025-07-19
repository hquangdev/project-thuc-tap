package org.example.javabt1.service.servicesimpl;

import lombok.RequiredArgsConstructor;
import org.example.javabt1.dto.ResponseBuilder;
import org.example.javabt1.dto.ResponseDto;
import org.example.javabt1.dto.request.ProductRequest;
import org.example.javabt1.dto.response.ProductResponse;
import org.example.javabt1.entity.Category;
import org.example.javabt1.entity.Product;
import org.example.javabt1.enums.Message;
import org.example.javabt1.repositoty.CategoryRepository;
import org.example.javabt1.repositoty.ProductRepository;
import org.example.javabt1.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<ResponseDto<Object>> addProduct(ProductRequest productRequest) {
        if(productRepository.existsByName(productRequest.getName())) {
            throw new RuntimeException("Đã tồn tại tên sản phẩm này");
        }

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục"));

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setQuantity(productRequest.getQuantity());
        product.setPrice(productRequest.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ResponseBuilder.okResponse(Message.ADD_PRODUCT_SUCCESS, product);
    }

    @Override
    public ResponseEntity<ResponseDto<Object>> updateProduct(Long id, ProductRequest updatedProduct) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm id: " + id));

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());
        product.setDescription(updatedProduct.getDescription());
        // Xử lý cập nhật danh mục
        if (updatedProduct.getCategoryId() != null) {
            Category category = categoryRepository.findById(updatedProduct.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục với id: " + updatedProduct.getCategoryId()));
            product.setCategory(category);
        }
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
    public ResponseEntity<ResponseDto<List<ProductResponse>>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductResponse> productResponses = productPage.getContent().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());

        return ResponseBuilder.okResponse(Message.GET_PRODUCT_SUCCESS, productResponses);

    }


}
