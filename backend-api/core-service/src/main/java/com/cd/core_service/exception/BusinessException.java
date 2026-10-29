package com.cd.core_service.exception;

import com.cd.core_service.enums.ErrorCode;

public class BusinessException extends RuntimeException{
    private final ErrorCode code;

    public BusinessException(ErrorCode code, Object... args) {
        super(code.format(args));
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
