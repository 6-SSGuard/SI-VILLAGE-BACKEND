package org.example.sivillage.question.vo.in;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.question.dto.in.ProductQuestionRequestDto;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuestionRequestVo {

    @Schema(description = "문의 내용", example = "배송 관련 문의합니다.", required = true)
    @NotNull(message = "상품 문의 내용은 필수입니다.")
    @Size(max = 1000, message = "문의 내용은 최대 1000자까지 입력할 수 있습니다.")
    private String questionContent;

    @Schema(description = "비밀글 여부", example = "true")
    @NotNull
    private boolean privateMessage;

    public static ProductQuestionRequestDto toDto(ProductQuestionRequestVo vo){
        return ProductQuestionRequestDto.builder()
                .questionContent(vo.getQuestionContent())
                .privateMessage(vo.isPrivateMessage())
                .build();
    }

}
