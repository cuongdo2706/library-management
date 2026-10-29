package com.cd.core_service.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "Không tìm thấy độc giả %s"),
    COPY_NOT_FOUND(HttpStatus.NOT_FOUND, "Không tìm thấy bản sao %s"),
    COPY_NOT_AVAILABLE(HttpStatus.CONFLICT, "Bản sao %s hiện không sẵn sàng để mượn");

    private final HttpStatus status;
    private final String messageTemplate;

    ErrorCode(HttpStatus status, String messageTemplate) {
        this.status = status;
        this.messageTemplate = messageTemplate;
    }

    public HttpStatus status() {
        return status;
    }
    public String format(Object... args) {
        return messageTemplate.formatted(args);
    }
}
