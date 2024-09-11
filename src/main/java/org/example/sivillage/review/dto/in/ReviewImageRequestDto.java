package org.example.sivillage.review.dto.in;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ReviewImageRequestDto {

    private List<String> reviewImageUrl;


    @Builder
    public ReviewImageRequestDto(List<String> reviewImageUrl) {
        this.reviewImageUrl = reviewImageUrl;
    }

}
