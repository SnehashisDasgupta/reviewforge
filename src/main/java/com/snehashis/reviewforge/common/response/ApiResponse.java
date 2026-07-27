package com.snehashis.reviewforge.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {

    private final boolean success;
    private final String message;
    private final T data;
    private final Instant timestamp;
}

/*Without @Builder
    new ApiResponse<>(
        true,
        "User created",
        userDto,
        Instant.now()
    );

  With @Builder
    ApiResponse<UserDto> response = ApiResponse.<UserDto>builder()
        .success(true)
        .message("User created successfully.")
        .data(userDto)
        .timestamp(Instant.now())
        .build();
*/