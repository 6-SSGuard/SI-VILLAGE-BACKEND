package org.example.sivillage.brand.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetBrandsListResponseVo {

    private Long brandId;
    private String brandEngName;
    private String brandKorName;
    private boolean isLiked;
    private String brandIndexLetter;
    private String brandIndexLetterKor;

    @Builder
    public GetBrandsListResponseVo(Long brandId, String brandEngName, String brandKorName, boolean isLiked, String brandIndexLetter, String brandIndexLetterKor) {
        this.brandId = brandId;
        this.brandEngName = brandEngName;
        this.brandKorName = brandKorName;
        this.isLiked = isLiked;
        this.brandIndexLetter = brandIndexLetter;
        this.brandIndexLetterKor = brandIndexLetterKor;
    }

}
