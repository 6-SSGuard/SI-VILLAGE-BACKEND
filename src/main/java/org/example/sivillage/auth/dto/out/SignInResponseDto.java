package org.example.sivillage.auth.dto.out;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.auth.vo.out.SignInResponseVo;

@Getter
@NoArgsConstructor
public class SignInResponseDto {

    private String accessToken;
    private String name;
    private String memberUuid;

    @Builder
    public SignInResponseDto(String accessToken, String name, String memberUuid) {
        this.accessToken = accessToken;
        this.name = name;
        this.memberUuid = memberUuid;
    }

    public SignInResponseVo toVo() {
        return SignInResponseVo.builder()
                .accessToken(accessToken)
                .name(name)
                .uuid(memberUuid)
                .build();
    }
}
