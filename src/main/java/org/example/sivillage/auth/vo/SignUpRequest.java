package org.example.sivillage.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.member.domain.Role;

@Getter
@NoArgsConstructor
public class SignUpRequest {
    @Schema(description = "이메일", example = "test1234@gmail.com", required = true)
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String email;

    @Schema(description = "비밀번호는 최소 8자 이상, 영문자, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다.", example = "!test1234", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 최소 8자 이상, 영문자, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다.")
    private String password;

    @Schema(description = "회원의 역할", example = "ADMIN", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "역할은 필수 입력 값입니다.")
    private Role role;

    @Schema(description = "이름은 2글자 이상이 되어야 합니다.", example = "민지훈", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "^.{2,}$", message = "이름은 2글자 이상이 되어야 합니다.")
    private String name;
}