package com.challenger.challengerbe.modules.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateRequest {

        @NotBlank(message = "idk 값은 필수 항목입니다.")
        String idk;

        @NotBlank(message = "이메일은 필수 항목입니다.")
        String email;

        @NotBlank(message = "이름은 필수 항목입니다.")
        String username;

        @Builder
        public UserCreateRequest(String idk, String email,  String username) {
                this.idk = idk;
                this.email = email;
                this.username = username;
        }
}
