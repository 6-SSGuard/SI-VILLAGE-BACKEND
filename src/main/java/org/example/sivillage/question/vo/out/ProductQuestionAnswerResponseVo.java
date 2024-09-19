package org.example.sivillage.question.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuestionAnswerResponseVo {

    private Long questionAnswerId;

    private String questionAnswerContent;
}
