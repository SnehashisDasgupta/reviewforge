package com.snehashis.reviewforge.user.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private final String accessToken;
    private final String tokenType;
}
