package org.example.sivillage.review.infrastructure;

import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.common.response.vo.IdListResponseVo;
import org.example.sivillage.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Long> findIdByProductCode(String productCode);
    List<Long> findIdByMemberUuid(String memberUuid);

}
