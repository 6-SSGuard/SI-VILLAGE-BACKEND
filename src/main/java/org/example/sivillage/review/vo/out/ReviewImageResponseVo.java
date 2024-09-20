package org.example.sivillage.review.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class    ReviewImageResponseVo {

    private Long reviewImageId;
    private String reviewImageUrl;

}
