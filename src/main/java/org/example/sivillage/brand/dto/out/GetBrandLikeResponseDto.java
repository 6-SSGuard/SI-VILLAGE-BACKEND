package org.example.sivillage.brand.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GetBrandLikeResponseDto {

    private Boolean like;

    @Builder
    public GetBrandLikeResponseDto(Boolean like) {
        this.like = like;
    }
}
