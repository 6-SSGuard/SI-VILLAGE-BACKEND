package org.example.sivillage.global.error;

import lombok.Getter;
import org.example.sivillage.global.common.response.BaseResponseStatus;

@Getter
public class BaseException extends RuntimeException {
    private final BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        super(status.getMessage()); //
        this.status = status;
    }
}
