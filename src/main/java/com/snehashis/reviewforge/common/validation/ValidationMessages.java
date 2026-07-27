package com.snehashis.reviewforge.common.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationMessages {

    public static final String EMAIL_REQUIRED =
            "Email is required.";

    public static final String EMAIL_INVALID =
            "Please provide a valid email address.";

    public static final String PASSWORD_REQUIRED =
            "Password is required.";

    public static final String PASSWORD_SIZE =
            "Password must be between 8 and 100 characters.";

    public static final String FULL_NAME_REQUIRED =
            "Full name is required.";

    public static final String FULL_NAME_SIZE =
            "Full name must be between 2 and 100 characters.";
}
