package org.example.sivillage.review.infrastructure;

import org.example.sivillage.review.domain.Review;
import org.example.sivillage.review.domain.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    List<Long>findByProductUuid(String productUuid);
    List<Long>findByMemberUuid(String memberUuid);

}
