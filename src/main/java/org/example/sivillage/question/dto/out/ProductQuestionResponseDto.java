package org.example.sivillage.question.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.question.domain.ProductQuestion;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductQuestionResponseDto {

    private Long productQuestionId;

    private String authorEmail;

    private LocalDateTime productQuestionDate;

    private String questionContent;

    private boolean privateMessage;

//    public static ProductQuestionResponseDto toDto(ProductQuestion productQuestion){
//        return ProductQuestionResponseDto.builder()
//                .productQuestionId(productQuestion.getProductQuestionId())
//                .authorEmail(productQuestion.getAuthorEmail().substring(0, 4) + "*******")
//                .productQuestionDate(productQuestion.getCreatedDate())
//                .questionContent(productQuestion.getQuestionContent())
//                .privateMessage(productQuestion.isPrivateMessage())
//                .build();
//
//    }

}
