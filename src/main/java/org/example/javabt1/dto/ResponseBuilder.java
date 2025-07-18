package org.example.javabt1.dto;


import org.example.javabt1.enums.Message;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseBuilder  {
    // Phương thức tĩnh để xây dựng ResponseDto với dữ liệu trả về
    public static <T> ResponseEntity<ResponseDto<T>> okResponse(Message message, T data) {
        ResponseDto<T> responseDto = ResponseDto.<T>builder()
                .code(message.getCode())
                .message(message.getMess())
                .data(data)
                .build();

        return new ResponseEntity<>(responseDto, message.getStatusCode());
    }

    public static <T> ResponseEntity<ResponseDto<List<T>>> okResponseWithPage(Message message, Page<T> pageData) {
        MetaData metaData = MetaData.builder()
                .totalItems(pageData.getTotalElements())
                .totalPage(pageData.getTotalPages())
                .currentPage(pageData.getNumber())
                .pageSize(pageData.getSize())
                .build();

        ResponseDto<List<T>> responseDto = ResponseDto.<List<T>>builder()
                .code(message.getCode())
                .message(message.getMess())
                .data(pageData.getContent())
                .metaData(metaData)
                .build();

        return new ResponseEntity<>(responseDto, message.getStatusCode());
    }

    public static <T> ResponseEntity<ResponseDto<T>> failedResponse(Message message) {
        ResponseDto<T> responseDto = ResponseDto.<T>builder()
                .code(message.getCode())
                .message(message.getMess())
                .build();

        return new ResponseEntity<>(responseDto, message.getStatusCode());
    }
}