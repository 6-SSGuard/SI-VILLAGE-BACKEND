package org.example.sivillage.review.application;


import org.example.sivillage.global.common.response.dto.IdListResponseDto;

import java.util.List;

public interface ReviewSortService {

    List<IdListResponseDto<Long>> getSortReviews(Long cursor, int pageSize, String sort);
}
