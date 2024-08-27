package org.example.sivillage.global.error;

import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<Void>> BaseError(BaseException e) {
        // BaseException의 BaseResponseStatus를 가져와서 BaseResponse를 만들어서 return
        BaseResponse<Void> response = new BaseResponse<>(e.getStatus());
        log.error("BaseException -> {} {})", e.getStatus(), e.getStatus().getMessage(), e);
        return new ResponseEntity<>(response, response.httpStatus());
    }

    /** security 인증 에러
     * 아이디 없거나 비번 틀린 경우 AuthenticationManager 에서 발생
     * @return FAILED_TO_LOGIN 에러 response
     */
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<BaseResponse<Void>> handleBadCredentialsException(BadCredentialsException e) {
        BaseResponse<Void> response = new BaseResponse<>(BaseResponseStatus.LOGIN_FAILURE);
        log.error("BadCredentialsException: ", e);
        return new ResponseEntity<>(response, response.httpStatus());
    }

    // 런타임 에러
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<BaseResponse<Void>> handleRuntimeException(RuntimeException e) {
        // BaseException으로 잡히지 않은 에러는 해당 에러로 처리해줌
        BaseResponse<Void> response = new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        log.error("RuntimeException: ", e);
        for (StackTraceElement s : e.getStackTrace()) {
            System.out.println(s);
        }
        return new ResponseEntity<>(response, response.httpStatus());
    }
}
