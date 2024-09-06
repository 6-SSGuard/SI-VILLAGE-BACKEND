package org.example.sivillage.question.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductQuestionRequestDto {

    private String authorEmail;

    private String questionContent;

    private boolean privateMessage;

    }

