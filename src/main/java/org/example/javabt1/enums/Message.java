package org.example.javabt1.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Message {

    //thông báo thành công
    REGISTER_SUCCESS(1000, "Bạn đã đăng ký tài khoản thành công", HttpStatus.OK),
    LOGIN_SUCCESS(1001, "Bạn đã đăng nhập thành công", HttpStatus.OK),
    GET_ALL_USER_SUCCESS(1002, "Bạn đã lấy danh sách tài khoản thành công", HttpStatus.OK),
    UPDATE_USER_SUCCESS(1003, "Bạn đã cập nhật tài khoản thành công", HttpStatus.OK),
    ADD_PRODUCT_SUCCESS(2000, "Bạn đã tạo sản phẩm thành công", HttpStatus.OK),
    GET_PRODUCT_SUCCESS(2001, "Bạn đã lấy sản phẩm thành công", HttpStatus.OK),
    DELETE_SUCCESS(2002, "Bạn đã xóa thành công", HttpStatus.OK),
    UPDATE_SUCCESS(2003, "Bạn đã cập nhật sản phẩm thành công", HttpStatus.OK),



    //thông báo thất bại/lỗi
    UNAUTHENTICATED(401, "Người dùng chưa đăng nhập hoặc phiên đã hết hạn", HttpStatus.UNAUTHORIZED);




    private final int code;
    private final String mess;
    private final HttpStatus statusCode;

    Message(int code, String mess, HttpStatus statusCode) {
        this.code = code;
        this.mess = mess;
        this.statusCode= statusCode;
    }
}
