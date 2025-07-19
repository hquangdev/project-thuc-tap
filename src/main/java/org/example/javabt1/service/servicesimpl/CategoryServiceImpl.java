package org.example.javabt1.service.servicesimpl;

import lombok.RequiredArgsConstructor;
import org.example.javabt1.dto.ResponseBuilder;
import org.example.javabt1.dto.ResponseDto;
import org.example.javabt1.dto.request.CategoryRequest;
import org.example.javabt1.dto.response.CategoryResponse;
import org.example.javabt1.entity.Category;
import org.example.javabt1.enums.Message;
import org.example.javabt1.repositoty.CategoryRepository;
import org.example.javabt1.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<ResponseDto<Object>> addCategory(CategoryRequest categoryRequest) {
        if(categoryRepository.existsByName(categoryRequest.getName())) {
            throw new RuntimeException("Đã tồn tại tên sản phẩm này");
        }
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setContent(categoryRequest.getContent());
        categoryRepository.save(category);
        return ResponseBuilder.okResponse(Message.ADD_CATEGORY_SUCCESS, category);
    }

    @Override
    public ResponseEntity<ResponseDto<Object>> updateCategory(Long id, CategoryRequest updatedCategory) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy id: " + id));

        category.setName(updatedCategory.getName());
        category.setContent(updatedCategory.getContent());

        categoryRepository.save(category);
        return ResponseBuilder.okResponse(Message.UPDATE_SUCCESS, category);
    }

    @Override
    public ResponseEntity<ResponseDto<Object>> deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm id: " + id));
        categoryRepository.deleteById(id);
        return ResponseBuilder.okResponse(Message.DELETE_SUCCESS, null);
    }

    @Override
    public ResponseEntity<ResponseDto<Object>> getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm id: " + id));

        CategoryResponse categoryOptional = new CategoryResponse(category);
        return ResponseBuilder.okResponse(Message.GET_PRODUCT_SUCCESS, categoryOptional);
    }
    
    
    @Override
    public ResponseEntity<ResponseDto<List<Category>>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        return ResponseBuilder.okResponseWithPage(Message.GET_PRODUCT_SUCCESS, categoryPage);
    }

}
