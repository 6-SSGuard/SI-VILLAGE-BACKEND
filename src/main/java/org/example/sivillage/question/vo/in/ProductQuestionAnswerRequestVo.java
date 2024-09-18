package org.example.sivillage.question.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.example.sivillage.question.dto.in.ProductQuestionAnswerRequestDto;

@Getter
public class ProductQuestionAnswerRequestVo {

    @Schema(description = "문의 답변 내용", example = "안녕하세요. 고객님\n" + "S.I VILLAGE 상품문의 담당자 ***입니다.\n", required = true)
    @NotNull
    @Size(max = 1000, message = "답변 내용은 최대 1000자까지 입력할 수 있습니다.")
    private String questionAnswerContent;


    public static ProductQuestionAnswerRequestDto toDto (ProductQuestionAnswerRequestVo productQuestionAnswerRequestVo){
        return ProductQuestionAnswerRequestDto.builder()
                .questionAnswerContent(productQuestionAnswerRequestVo.getQuestionAnswerContent())
                .build();
    }
}
