package com.snehashis.reviewforge.common.exception;

import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class ErrorResponseFactory {

    public static ErrorResponse createErrorResponse(String message){
        return ErrorResponse.builder()
                .success(false)
                .message(message)
                .errors(Collections.emptyList())
                .timestamp(Instant.now())
                .build();
    }

    public static ErrorResponse createErrorResponse(String message, List<ApiError> errors){
        return ErrorResponse.builder()
                .success(false)
                .message(message)
                .errors(errors)
                .timestamp(Instant.now())
                .build();
    }
}
