package org.example.sivillage.product.vo.in;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductQuestionRequestVo {

    @Schema(description = "문의 내용", example = "배송 관련 문의합니다.", required = true)
    @NotNull(message = "상품 문의 내용은 필수입니다.")
    @Size(max = 2000, message = "문의 내용은 최대 2000자까지 입력할 수 있습니다.")
    private String questionContent;

    @Schema(description = "비밀글 여부", example = "true")
    @NotNull
    private boolean isPrivate;

}
