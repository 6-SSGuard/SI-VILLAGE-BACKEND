package org.example.sivillage.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponseEntity<T> {
    private HttpStatus status;
    private T data;
    private String message;

    // message만 반환하는 생성자
    public CustomResponseEntity(HttpStatus httpStatus, String message) {
        this.status = httpStatus;
        this.data = null;
        this.message = message;
    }
}
// Response음ntity에 종속되는 문제가 있