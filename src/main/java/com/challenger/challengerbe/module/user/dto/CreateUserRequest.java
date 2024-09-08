package com.challenger.challengerbe.module.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest (
        @NotBlank(message = "idk 값은 필수 항목입니다.")
        String idk,
        @NotBlank(message = "이메일은 필수 항목입니다.")
        String email,
        @NotBlank(message = "비밀번호는 필수 항목입니다.")
        String password,
        @NotBlank(message = "이름은 필수 항목입니다.")
        String username,
        @NotBlank(message = "role은 필수 항목입니다.")
        String role
) {

}
