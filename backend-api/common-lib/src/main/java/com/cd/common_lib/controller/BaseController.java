package com.cd.common_lib.controller;

import com.cd.common_lib.dto.response.ApiResponse;

public abstract class BaseController {
    protected <T>ApiResponse<T> createSuccessResponse(T data){
        return ApiResponse.success(data);
    }
}
