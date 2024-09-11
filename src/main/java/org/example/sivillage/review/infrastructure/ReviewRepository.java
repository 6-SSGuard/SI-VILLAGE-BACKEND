package org.example.sivillage.review.infrastructure;

import org.example.sivillage.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Long> findByProductUuid(String productUuid);
    List<Long> findByMemberUuid(String memberUuid);


}
