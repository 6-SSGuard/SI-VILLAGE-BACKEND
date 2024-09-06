package org.example.sivillage.question.vo.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.question.dto.out.ProductQuestionResponseDto;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuestionResponseVo {

    private Long productQuestionId;

    private String authorEmail;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private LocalDateTime productQuestionDate;

    private String questionContent;

    private boolean privateMessage;

    public static ProductQuestionResponseVo toVo(ProductQuestionResponseDto dto){
        return ProductQuestionResponseVo.builder()
                .productQuestionId(dto.getProductQuestionId())
                .authorEmail(dto.getAuthorEmail())
                .productQuestionDate(dto.getProductQuestionDate())
                .questionContent(dto.getQuestionContent())
                .privateMessage(dto.isPrivateMessage())
                .build();

    }
}
