package com.snehashis.reviewforge.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiError {

    private final String field;
    private final String message;
}
