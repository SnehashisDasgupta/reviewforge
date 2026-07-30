package com.snehashis.reviewforge.user.dto.request;

import com.snehashis.reviewforge.common.validation.ValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterRequest {

    @NotBlank(message = ValidationMessages.FULL_NAME_REQUIRED)
    @Size(
            min = 2,
            max = 100,
            message = ValidationMessages.FULL_NAME_SIZE
    )
    private String fullName;

    @NotBlank(message = ValidationMessages.EMAIL_REQUIRED)
    @Email(message = ValidationMessages.EMAIL_INVALID)
    @Size(
            max = 150,
            message = ValidationMessages.EMAIL_SIZE
    )
    private String email;

    @NotBlank(message = ValidationMessages.PASSWORD_REQUIRED)
    @Size(
            min = 8,
            max = 100,
            message = ValidationMessages.PASSWORD_SIZE
    )
    private String password;

    @NotBlank(message = ValidationMessages.PASSWORD_CONFIRM_REQUIRED)
    private String confirmPassword;
}
