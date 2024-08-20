package org.example.sivillage.domain.member.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class LogInRequest {
    @Schema(description = "이메일", example = "test1234@gmail.com")
    @NotBlank(message = "이메일은 필수 값입니다.")
    private String email;

    @Schema(description = "비밀번호", example = "!test1234")
    @NotBlank(message = "비밀번호는 필수 값입니다.")
    private String password;
}