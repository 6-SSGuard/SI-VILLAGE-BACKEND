package org.example.sivillage.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {

    private String reviewContent;

    private Double score;

    private List<String> reviewImageUrl;

}
