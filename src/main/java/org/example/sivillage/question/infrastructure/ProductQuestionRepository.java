package org.example.sivillage.question.infrastructure;

import org.example.sivillage.question.domain.ProductQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductQuestionRepository extends JpaRepository<ProductQuestion, Long> {
    List<ProductQuestion> findByProductCode(String productCode);
    List<ProductQuestion> findByMemberUuid(String productUuid);
}
