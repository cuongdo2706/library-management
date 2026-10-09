package com.cd.catalog_service.exception;

import com.cd.common_lib.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Map<String, String>> handlerValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );
        return ApiResponse.errorListDataMessages(HttpStatus.BAD_REQUEST.value(),"List of errors",errors);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ApiResponse<Object>>handlerRuntimeException(RuntimeException ex){
//       ApiResponse<Object>apiResponse = ApiResponse.error();
//    }
}
