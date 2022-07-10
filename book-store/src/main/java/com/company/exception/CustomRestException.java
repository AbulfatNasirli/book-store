package com.company.exception;

import com.company.enums.ErrorCodeEnum;

public class CustomRestException extends RuntimeException {
   private final String message;
   private final int code;
    public CustomRestException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.message=errorCodeEnum.getMessage();
        this.code=errorCodeEnum.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
