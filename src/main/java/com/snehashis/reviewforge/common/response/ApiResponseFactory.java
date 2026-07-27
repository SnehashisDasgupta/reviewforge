package com.snehashis.reviewforge.common.response;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponseFactory {

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    public static ApiResponse<Void> success(String message) {
        return ApiResponse.<Void>builder()
                .success(true)
                .message(message)
                .data(null)
                .timestamp(Instant.now())
                .build();
    }
}

/*
    Why @NoArgsConstructor(access = PRIVATE)?
     This prevents accidental instantiation.
     We don't want anyone writing:
        new ApiResponseFactory();
     Instead, use:
        ApiResponseFactory.success(...);
*/